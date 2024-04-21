DROP DATABASE IF EXISTS ForumData;
CREATE DATABASE ForumData;
USE ForumData;
CREATE TABLE ForumGroups (
    fgid INT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE User (
    userid INT PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE ForumDiscussions (
    titleid INT PRIMARY KEY,
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
    commentid INT PRIMARY KEY,
    userid INT,
    comment VARCHAR(2000),
    titleid INT,
    creationtime DATETIME,
    FOREIGN KEY (userid) REFERENCES User(userid),
    FOREIGN KEY (titleid) REFERENCES ForumDiscussions(titleid)
);
