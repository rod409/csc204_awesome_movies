CREATE TABLE Movie (
	id  INT NOT NULL,
	title VARCHAR(128),
	imdbPictureURL VARCHAR(128),
	`year` YEAR,
	rtAudienceRating Decimal(5, 1),
	rtAudienceNumRatings INT,
	rtAudienceScore SMALLINT,
	rtPictureURL VARCHAR(128),
	directorID VARCHAR(64),
	country VARCHAR(32),
	PRIMARY KEY (id)
);

CREATE TABLE Person (
	id VARCHAR(64) NOT NULL,
	`name` VARCHAR(64), 
	PRIMARY KEY (id)
);

CREATE TABLE Tag (
	id INT,
	value VARCHAR(64), 
	PRIMARY KEY (id)
);

CREATE TABLE Movie_Actor(
	movieID INT NOT NULL,
    actorID VARCHAR(64) NOT NULL,
    ranking SMALLINT,
    PRIMARY KEY(movieID, actorID)
);

CREATE TABLE Movie_Tag(
	movieID INT NOT NULL,
    tagID INT NOT NULL,
    tagWeight INT,
    PRIMARY KEY(movieID, tagID)
);

CREATE TABLE User_Rated_Movie (
	userID INT NOT NULL,
    movieID INT NOT NULL,
    rating DECIMAL(5,1),
    `timestamp` TIMESTAMP,
    PRIMARY KEY(userID, movieID)
);

CREATE TABLE User_Tagged_Movie (
	userID INT NOT NULL,
    movieID INT NOT NULL,
    tagID INT NOT NULL,
    `timestamp` TIMESTAMP,
    PRIMARY KEY(userID, movieID, tagID)
);

CREATE TABLE Movie_Genre(
	movieID INT NOT NULL,
    Genre VARCHAR(32) NOT NULL,
    PRIMARY KEY(movieID, Genre)
);

CREATE TABLE Movie_Location(
	movieID INT NOT NULL,
    location VARCHAR(64) NOT NULL,
    PRIMARY KEY(movieID, location)
);

ALTER TABLE Movie
	ADD FOREIGN KEY(directorID) REFERENCES Person(id);
    
ALTER TABLE Movie_Actor
	ADD FOREIGN KEY(movieID) REFERENCES Movie(id),
    ADD FOREIGN KEY(actorID) REFERENCES Person(id);
    
ALTER TABLE Movie_Tag
	ADD FOREIGN KEY(movieID) REFERENCES Movie(id),
    ADD FOREIGN KEY(tagID) REFERENCES Tag(id);
    
ALTER TABLE User_Rated_Movie
	ADD FOREIGN KEY(movieID) REFERENCES Movie(id);

ALTER TABLE User_Tagged_Movie
	ADD FOREIGN KEY(movieID) REFERENCES Movie(id),
    ADD FOREIGN KEY(tagID) REFERENCES Tag(id);

ALTER TABLE Movie_Genre
	ADD FOREIGN KEY(movieID) REFERENCES Movie(id);
    
ALTER TABLE Movie_Location
	ADD FOREIGN KEY(movieID) REFERENCES Movie(id);
