SELECT title, imdbPictureURL, `year`, rtAudienceScore, rtPictureURL
FROM Movie
ORDER BY rtAudienceScore DESC
LIMIT 10;

SELECT M.id, M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL, T.value
FROM Movie as M, Tag as T, Movie_Tag as MT
WHERE M.title LIKE '%Toy%' AND MT.movieID = M.id AND MT.tagID = T.id
ORDER BY rtAudienceScore DESC;

SELECT M.id, M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL
FROM Movie as M, Movie_Genre as MG
WHERE MG.Genre = 'Comedy' AND MG.movieID = M.id
ORDER BY rtAudienceScore DESC
Limit 10;

SELECT M.id, M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL
FROM Movie as M, Person as P
WHERE P.name LIKE "%Stone%" AND P.id = M.directorID;

SELECT M.id, M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL
FROM Movie as M, Person as P, Movie_Actor as MA
WHERE P.name LIKE "%Will Smith%" AND P.id = MA.actorID AND M.id = MA.movieID;

SELECT M.id, M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL, T.value
FROM Movie as M, Tag as T, Movie_Tag as MT
WHERE T.value LIKE '%robots%' AND MT.movieID = M.id AND MT.tagID = T.id
ORDER BY rtAudienceScore DESC;

SELECT P.name, AVG(M.rtAudienceScore) as Rating
FROM Person as P, Movie as M
WHERE M.directorID = P.id
GROUP BY P.name
Having COUNT(*) >= 5
ORDER BY Rating DESC
LIMIT 10;

SELECT P.name, AVG(M.rtAudienceScore) as Rating
FROM Person as P, Movie as M, Movie_Actor as MA
WHERE M.id = MA.movieID AND P.id = MA.actorID
GROUP BY P.name
Having COUNT(*) >= 5
ORDER BY Rating DESC
LIMIT 10;

SELECT M.id, M.title, MG.Genre, URM.timestamp, URM.rating
FROM Movie as M, User_Rated_Movie as URM, Movie_Genre as MG
WHERE URM.userID = 78 AND URM.movieID = M.id AND M.id = MG.movieID
ORDER BY URM.timestamp DESC, M.id;
