CREATE TABLE category
(
    id             INT AUTO_INCREMENT NOT NULL,
    category_title VARCHAR(450) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE question
(
    id               INT AUTO_INCREMENT NOT NULL,
    question_title   VARCHAR(450) NOT NULL,
    option1          VARCHAR(255) NULL,
    option2          VARCHAR(255) NULL,
    option3          VARCHAR(255) NULL,
    option4          VARCHAR(255) NULL,
    right_answer     VARCHAR(255) NULL,
    difficulty_level VARCHAR(255) NULL,
    category_id      INT NULL,
    CONSTRAINT pk_question PRIMARY KEY (id)
);

CREATE TABLE quiz
(
    id    INT AUTO_INCREMENT NOT NULL,
    title VARCHAR(255) NULL,
    CONSTRAINT pk_quiz PRIMARY KEY (id)
);

CREATE TABLE quiz_questions
(
    questions_id INT NOT NULL,
    quiz_id      INT NOT NULL,
    CONSTRAINT pk_quiz_questions PRIMARY KEY (questions_id, quiz_id)
);

ALTER TABLE category
    ADD CONSTRAINT uc_category_category_title UNIQUE (category_title);

ALTER TABLE question
    ADD CONSTRAINT uc_question_question_title UNIQUE (question_title);

ALTER TABLE question
    ADD CONSTRAINT FK_QUESTION_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE quiz_questions
    ADD CONSTRAINT fk_quique_on_question FOREIGN KEY (questions_id) REFERENCES question (id);

ALTER TABLE quiz_questions
    ADD CONSTRAINT fk_quique_on_quiz FOREIGN KEY (quiz_id) REFERENCES quiz (id);

INSERT INTO category (category_title) VALUES ('java'), ('python'), ('c#');

INSERT INTO question (question_title, option1, option2, option3, option4, right_answer, difficulty_level, category_id)
VALUES
    ('What is a class in Java?', 'A function', 'An object', 'A data structure', 'A loop', 'An object', 'Easy', 1),
    ('What does OOP stand for?', 'Object-Oriented Programming', 'Object Ordering Process', 'Operating Overloaded Pointers', 'Order of Operations', 'Object-Oriented Programming', 'Easy', 1),
    ('What is a list in Python?', 'A type of loop', 'A built-in function', 'A data structure', 'An object', 'A data structure', 'Easy', 2),
    ('Which data structure uses First-In-First-Out (FIFO) order?', 'Stack', 'Queue', 'Array', 'LinkedList', 'Queue', 'Medium', 2),
    ('What is a constructor?', 'A member of a class', 'A loop in Python', 'A data type', 'A special method', 'A special method', 'Medium', 1),
    ('Which sorting algorithm has the worst-case time complexity of O(n^2)?', 'Merge Sort', 'Quick Sort', 'Insertion Sort', 'Bubble Sort', 'Bubble Sort', 'Hard', 2),
    ('In Java, what is used to create an instance of a class?', 'Class', 'Method', 'Object', 'Constructor', 'Constructor', 'Easy', 1),
    ('Which keyword is used to define a variable that won’t be reassigned?', 'static', 'final', 'constant', 'immutable', 'final', 'Easy', 1),
    ('What is the output of 4 ^ 3 in Python?', '7', '64', '81', '12', '64', 'Easy', 2),
    ('What does the term "polymorphism" refer to in programming?', 'Using multiple inheritance', 'Ability to take multiple forms', 'Manipulating data', 'Using multiple programming languages', 'Ability to take multiple forms', 'Medium', 1),
    ('What is the purpose of the "self" parameter in Python class methods?', 'It refers to the current instance of the class', 'It is used to call parent class methods', 'It is a keyword for loops', 'It is a data type', 'It refers to the current instance of the class', 'Medium', 2),
    ('Which of the following is not a primitive data type in Java?', 'int', 'boolean', 'char', 'string', 'string', 'Medium', 1),
    ('What is the time complexity of a binary search?', 'O(n)', 'O(log n)', 'O(n^2)', 'O(1)', 'O(log n)', 'Medium', 2),
    ('What keyword is used to inherit a class in Python?', 'extends', 'inherits', 'super', 'class', 'class', 'Easy', 2),
    ('Which type of loop is ideal for situations where the number of iterations is known?', 'for loop', 'while loop', 'do-while loop', 'until loop', 'for loop', 'Easy', 1),
    ('What is the purpose of "import" in Python?', 'To export data', 'To create a backup', 'To include external modules', 'To print output', 'To include external modules', 'Easy', 2),
    ('In Java, which access modifier provides the widest visibility?', 'public', 'private', 'protected', 'package-private', 'public', 'Easy', 1),
    ('What is a lambda function in Python?', 'A function that uses the "lambda" keyword', 'A function with multiple return values', 'A function with no parameters', 'An anonymous inline function', 'An anonymous inline function', 'Medium', 2),
    ('What is a linked list?', 'A type of array', 'A linear data structure', 'A collection of objects', 'A group of classes', 'A linear data structure', 'Medium', 1),
    ('Which operator is used to concatenate strings in Python?', '&', '+', '*', '++', '+', 'Easy', 2),
    ('What does JVM stand for?', 'Java Virtual Machine', 'Just Virtual Memory', 'JavaScript Virtual Machine', 'Java Version Manager', 'Java Virtual Machine', 'Easy', 1),
    ('In Python, what is the main difference between a tuple and a list?', 'Tuples are mutable, lists are not', 'Lists are ordered, tuples are not', 'Tuples can store mixed data types, lists cannot', 'Lists have a fixed size, tuples do not', 'Tuples can store mixed data types, lists cannot', 'Medium', 2),
    ('What is the purpose of the "finally" block in a try-catch-finally statement?', 'To handle exceptions', 'To define a fallback value', 'To execute code regardless of exceptions', 'To terminate the program', 'To execute code regardless of exceptions', 'Medium', 1),
    ('What is a dictionary in Python?', 'A sorted collection of elements', 'A data structure used for searching', 'An ordered sequence of elements', 'A key-value store', 'A key-value store', 'Easy', 2),
    ('Which keyword is used to define a subclass in Java?', 'child', 'extends', 'inherits', 'subclass', 'extends', 'Easy', 1),
    ('What is the purpose of the "pass" statement in Python?', 'To stop the execution of a loop', 'To indicate an empty code block', 'To raise an exception', 'To terminate the program', 'To indicate an empty code block', 'Easy', 2);
