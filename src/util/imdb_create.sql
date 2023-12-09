# 1、创建数据库
CREATE DATABASE IF NOT EXISTS imdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 使用imdb数据库
USE imdb;

# 修改配置数据库字符集

ALTER DATABASE imdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
