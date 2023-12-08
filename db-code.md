# 建库过程

## 数据库配置

```sql
$ mysql -u root -p
# password is root
mysql> use imdb;
```

## 建表

### 1 电影表

```sql
create table Films(
	`id`			INT auto_increment,
    `name`			varchar(20) not null,
    `description`	 varchar(256),
    `director`		varchar(20),
    `runtime`		time,
    ``
)
```



### 2 用户表

### 3 演员表

### 4 评分表

### 5 评论表

