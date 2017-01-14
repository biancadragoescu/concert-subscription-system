DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	username varchar(25) not null,
    password varchar(20) not null,
    isAdmin boolean not null,
    primary key(username)
);

DROP TABLE IF EXISTS `passes`;
CREATE TABLE `passes` (
	id mediumint not null auto_increment,
    price double not null,
    remainingNumberOfConcerts int not null,
    primary key(id)
);

DROP TABLE IF EXISTS `usersInformation`;
CREATE TABLE `usersInformation` (
	username varchar(25) not null,
    name varchar(25) not null,
    idPass mediumint,
    foreign key(username) references users(username),
    foreign key(idPass) references passes(id)
);
    
DROP TABLE IF EXISTS `concerts`;
CREATE TABLE `concerts` (
	id mediumint not null auto_increment,
    name varchar(100) not null,
    price double not null,
    date date not null,
    musicGenre varchar(30) not null,
    artists varchar(200) not null,
    initialNumberOfSeats int not null,
    numberOfAvailableSeats int not null,
    primary key(id)
);

DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews` (
	id  mediumint not null auto_increment,
    username varchar(25) not null,
    idConcert mediumint not null,
    date date not null,
    title varchar(100) not null,
    comment varchar(500) not null,
    rating int not null,
    constraint rating check (rating in (1,2,3,4,5)),
    foreign key(username) references users(username),
    foreign key(idConcert) references concerts(id),
    primary key(id)
);

DROP TABLE IF EXISTS `concertspasses`;
create table `concertspasses` (
	idConcert mediumint not null,
    idPass mediumint not null,
    foreign key(idConcert) references concerts(id),
    foreign key(idPass) references passes(id),
    primary key(idConcert, idPass)
);

insert into users(username, password, isAdmin) values
	('cristinaDragoescu', 'cristinadragoescu', false),
    ('razvanMoldovan', 'razvanmoldovan', false),
    ('ligiaTosa', 'ligiatosa', false),
    ('administrator', 'administrator', true);
    
insert into usersinformation(username, name) values
	('cristinaDragoescu' , 'Bianca Cristina Dragoescu'),
    ('razvanMoldovan', 'Moldovan Razvan'),
    ('ligiaTosa', 'Ligia Tosa');
    
insert into concerts(name, price, date, musicGenre, artists, initialNumberOfSeats, numberOfAvailableSeats) values
	('Linkin Park', 500, '2017-04-30', 'pop-rock', 'Chester Bennington, Mike Shinoda, Brad Delson, Dave "Phoenix" Farrell, Joe Hahn, and Rob Bourdon', 700, 700),
    ('Lady Gaga', 450, '2017-02-23',  'pop', 'Gaga', 900, 900),
    ('Backstreet Boys', 300, '2017-09-21', 'pop dance-pop R&B', 'Nick Carter, Kevin Richardson, Brian Littrell, Howie D. and A.J. McLean', 470, 470);
    
insert into reviews(username, idConcert, date, title, comment, rating) values 
	('razvanMoldovan', 2, '2017-02-28', 'Pretty Much Amazing', 'Joanne still represents a striking course correction for Lady Gaga. By abandoning the dance club for the dive bar, she may have tossed aside her status as a pop star once and for all. But Gaga has emerged as something better and truer.', 5);