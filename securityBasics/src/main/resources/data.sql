INSERT INTO users(username, password, enabled)
    values('user1','1234',true);

INSERT INTO users(username, password, enabled)
    values('user2','1234',true);

INSERT INTO authorities(username, authority)
    values('user1','ROLE_USER');

INSERT INTO authorities(username, authority)
    values('user2','ROLE_ADMIN');