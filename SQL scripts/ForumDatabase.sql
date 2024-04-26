DROP DATABASE IF EXISTS ForumData;
CREATE DATABASE ForumData;
USE ForumData;
CREATE TABLE ForumGroups (
    fgid INT PRIMARY KEY  NOT NULL AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE User (
    userid INT PRIMARY KEY  NOT NULL AUTO_INCREMENT,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    points INT
);

CREATE TABLE ForumDiscussions (
    titleid INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(100),
    fgid INT,
    userid INT,
    post VARCHAR(2000),
    creationtime DATETIME,
    likes INT DEFAULT 0,
    FOREIGN KEY (fgid) REFERENCES ForumGroups(fgid),
    FOREIGN KEY (userid) REFERENCES User(userid)
);

CREATE TABLE Comments (
    commentid INT PRIMARY KEY  NOT NULL AUTO_INCREMENT,
    userid INT,
    comment VARCHAR(2000),
    titleid INT,
    creationtime DATETIME,
    likes INT DEFAULT 0,
    FOREIGN KEY (userid) REFERENCES User(userid),
    FOREIGN KEY (titleid) REFERENCES ForumDiscussions(titleid)
);

-- --Assuming 6 groups

 INSERT INTO forumgroups(name)
 VALUES ('SWE'), ('Product Management'), ('Quant'), ('MAANG'), ('Data Science'), ('Hardware');
 
 
-- Create sample user
 INSERT INTO user (username, email, password)
VALUES ('ttrojan', 'ttrojan@usc.edu', 'gosc');

-- Create HardCoded data
-- --
-- 1) ForumDiscussions (one for each group)
INSERT INTO ForumDiscussions (title, fgid, userid, post, creationtime)
VALUES
('Is it bad to renege on my full-time offer for another company?', 
 (SELECT fgid FROM ForumGroups WHERE name = 'SWE'), 
 1, 
 'I recently accepted a full-time job offer, but another opportunity just came up. I know reneging can damage my reputation, but I also don\'t want to miss out on a better opportunity. What do you think is the right thing to do in this situation?',
 NOW()),

('How do you effectively manage product roadmaps?', 
 (SELECT fgid FROM ForumGroups WHERE name = 'Product Management'), 
 1, 
 'Managing a product roadmap can be challenging, especially with changing priorities and customer demands. I\'m curious about the best practices for keeping roadmaps on track. Do you have any tips or tools that help you stay organized and focused?',
 NOW()),

('What strategies do you use to hedge financial risks?', 
 (SELECT fgid FROM ForumGroups WHERE name = 'Quant'), 
 1, 
 'I\'m exploring different ways to hedge financial risks in a quant environment. Are there specific instruments or strategies you find most effective? I would appreciate hearing about your experiences and any advice you can offer for managing risk in a quantitative context.',
 NOW()),

('How do you navigate career growth in MAANG companies?', 
 (SELECT fgid FROM ForumGroups WHERE name = 'MAANG'), 
 1, 
 'Working in a MAANG company has its benefits, but career growth can be challenging. What are some good ways to advance your career in these large tech firms? I would love to hear any success stories or tips for navigating the internal culture and processes.',
 NOW()),

('What data science tools are essential for success?', 
 (SELECT fgid FROM ForumGroups WHERE name = 'Data Science'), 
 1, 
 'There are so many data science tools available today, it can be hard to know which ones are must-haves. I\'m looking to streamline my workflow. What tools do you find indispensable for your data science projects?',
 NOW()),

('What are the latest trends in hardware innovation?', 
 (SELECT fgid FROM ForumGroups WHERE name = 'Hardware'), 
 1, 
 'Hardware technology is constantly evolving, with new trends emerging all the time. What are some recent innovations that you find exciting? I would love to discuss what\'s coming up in hardware and what it means for the industry.',
 NOW());


-- 2) Comments

INSERT INTO comments(userid, comment, titleid, creationtime, likes)
VALUES
(1, 
 'I understand your dilemma. It might be better to honor your commitment, but sometimes new opportunities are too good to pass up. Consider reaching out to the original employer to explain your situation and see if there\'s a way to part amicably.', 
 1, NOW(), 10),

(1, 'When managing product roadmaps, I focus on communication and flexibility. Clear communication with stakeholders is key to success, and having flexibility allows for changes as required. I also use tools like Trello and JIRA to keep track of tasks and deadlines.', 
 2, NOW(), 7),

(1, 
 'Hedging strategies vary depending on the type of risk you\'re dealing with. I often use options and futures contracts for hedging in quantitative finance. It\'s crucial to understand the risks involved with each strategy before implementing them.', 
 3, NOW(), 4),

(1, 'Career growth in MAANG companies often requires proactivity. It\'s not just about doing a good job; you must also build a strong network and take on new challenges. Mentorship can also be a great way to navigate your career path within these large organizations.', 
4, NOW(), 1),

(1, 
 'For data science, I find Python and its libraries like Pandas and NumPy to be indispensable. Tools like Jupyter Notebooks and TensorFlow are also great for specific use cases. It\'s essential to choose tools that fit your project\'s scope and complexity.', 
 5, NOW(),  0),

(1, 
 'In hardware innovation, I\m excited about the growing trend toward miniaturization and energy efficiency. We are seeing new materials and processes that make hardware more compact and sustainable. These trends open up a lot of opportunities for future development.', 
6, NOW(),  5);

