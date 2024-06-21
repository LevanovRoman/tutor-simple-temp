package com.testing.questions_history.event.listener;

import com.testing.questions_history.event.RegistrationCompleteEvent;
import com.testing.questions_history.registration.token.VerificationTokenService;
import com.testing.questions_history.user.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final VerificationTokenService tokenService;
    private final JavaMailSender mailSender;
    private User user;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. get the user
        user = event.getUser();
        // 2. generate a token for the user
        String vToken = UUID.randomUUID().toString();
        // 3. save the token for the user
        tokenService.saveVerificationTokenForUser(user, vToken);
        // 4. build the verification url
        String url = event.getConfirmationUrl() + "/registration/verifyEmail?token=" + vToken;
        // 5. send the email to the user
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Подтверждение электронной почты";
        String senderName = "Северная верфь. Служба верификации пользователей";
        String mailContent = "<p> Привет, "+ user.getFirstName()+ ", </p>"+
                "<p>Спасибо за регистрацию,"+"" +
                "Пожалуйста, пройдите по ссылке для завершения регистрации.</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Спасибо <br> Сервис Портала регистрации пользователей";
        emailMessage(subject, senderName, mailContent, mailSender, user);
    }

    public void sendPasswordResetVerificationEmail(String url, User user) throws MessagingException, UnsupportedEncodingException {
        String subject = "Подтверждение запроса на сброс пароля";
        String senderName = "Северная верфь. Служба верификации пользователей";
        String mailContent = "<p> Hi, "+ user.getFirstName()+ ", </p>"+
                "<p><b>Вы недавно запрашивали восстановление пароля,</b>"+"" +
                "Пожалуйста, пройдите по ссылке для завершения.</p>"+
                "<a href=\"" +url+ "\">Reset password</a>"+
                "<p> Сервис Портала регистрации пользователей";
        emailMessage(subject, senderName, mailContent, mailSender, user);
    }

    private static void emailMessage(String subject, String senderName,
                                     String mailContent, JavaMailSender mailSender, User theUser)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("roman197t@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
