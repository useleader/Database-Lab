
# 2、创建电影信息表
create table film(
    film_id int not null AUTO_INCREMENT,
    name varchar(20) not null,
    description varchar(256) not null,
    director varchar(20) not null,
    photo text,
    duration TIME not null,
    region varchar(20) not null,
    primary key (film_id)
)ENGINE=InnoDB AUTO_INCREMENT=1;

# 3、创建评论信息表
create table comment(
    comment_id int not null AUTO_INCREMENT,
    content varchar(200) not null,
    film_id int not null,
    primary key (comment_id),
    foreign key (film_id) references film(film_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 ;

# 4、创建演员信息表
create table actor(
    actor_id int not null AUTO_INCREMENT,
    name varchar(20) not null,
    photo text,
    age int not null,
    constraint c1 check (age between 0 and 100),
    primary key (actor_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 ;

# 5、创建参演电影信息表
create table perform(
    film_id int not null,
    actor_id int not null,
    primary key (film_id,actor_id),
    foreign key (film_id) references film(film_id),
    foreign key (actor_id) references actor(actor_id)
)ENGINE=InnoDB;

# 6、创建用户信息表
create table user(
    user_id int not null AUTO_INCREMENT,
    name varchar(20) not null,
    password varchar(256) not null,
    photo text,
    gender varchar(6)  not null,
    constraint c2 check (gender in ('male','female')),
    description varchar(256) DEFAULT null,
    email varchar(25) DEFAULT null,
    primary key (user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1;

# 7、创建评分信息表
create table score(
    film_id int not null,
    user_id int not null,
    number float not null,
    constraint c3 check (number between 0 and 10),
    primary key (film_id,user_id),
    foreign key (film_id) references film(film_id),
    foreign key (user_id) references user(user_id)
)ENGINE=InnoDB;

# 8、创建标签信息表
create table tag(
    name varchar(10) not null,
    film_id int not null,
    primary key (name,film_id),
    foreign key (film_id) references film(film_id)
)ENGINE=InnoDB;