DROP TABLE IF EXISTS file_info;
CREATE TABLE file_info(ID VARCHAR(255) PRIMARY KEY,
NAME VARCHAR(255)
, fileid VARCHAR(255)
, filesize VARCHAR(255)
, project VARCHAR(255)
, type VARCHAR(255)
, upload_time VARCHAR(255)
, contenttype VARCHAR(255)
, filetype VARCHAR(255))