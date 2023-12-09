# 代码解释

目前`src`文件夹中存放的是后端的代码，`web`中存放的是前端的代码；
`src`实现的框架是`servlet`，通过`tomcat`部署；
`web`实现的框架是`vue`，通过`npm`部署；

前端后端通过`axios`实现对接

## 后端介绍

- dao文件夹
  - 实现数据库与后端的交互
    - UserDao
    - FilmDao
    - ActorDao
    - CommentDao
    - ScoreDao
    - TagDao
    - PerformDao
- entity文件夹
  - 存储数据实体
    - User
    - Film
    - Actor
    - Comment
    - Score
    - Tag
    - Perform
- servlet文件夹
  - 实现前端与后端的交互
    - User文件夹
      - InsertServlet
      - DeleteServlet
      - UpdateServlet
      - SelectByIdServlet
      - SelectAllServlet
    - Film文件夹
      - InsertServlet
      - DeleteServlet
      - UpdateServlet
      - SelectByNameServlet
      - SelectByCombiningServlet
      - SelectAllServlet
    - Actor文件夹
      - InsertServlet
      - DeleteServlet
      - UpdateServlet
      - SelectByNameServlet
    - Comment文件夹
      - InsertServlet
      - DeleteServlet
    - Score文件夹
      - InsertServlet
    - Tag文件夹
      - SelectByNameServlet
    - Perform文件夹
      - SelectByActorServlet
      - SelectByFilmServlet
      - InsertServlet
- util文件夹
  - DBConnectionUtil
    - 连接数据库

## 前端介绍


## 前后端交流手册

- 需要前端提供给后端的东西
  - 每个页面对应的router
  - 具体需要的参数，可以更新在apifox中
  - 我目前的功能已经建立好了，但是代码还没写，你可以根据文件名确定好你的页面安排，同时Film的查询还没完善，还有别的查询条件没想好
  - 
- 前端须知
  - 后端使用的数据库在我本地
  - 如果你想要测试的话，一方面可以通过apifox自己构造请求，一方面你可以本地配一个数据库试试
  - 用户名root，password为root
  - 用户、电影、演员等类的照片需要更新一下资源路径，改成访问url那种，不要将图片放在assets里面，因为咱们照片在数据库中以url形式存储了
  - 