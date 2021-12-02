<h1><div style="text-align: center;">Learn_JavaWeb</div></h1>

## 尚硅谷JavaWeb书城项目

该项目使用了JavaWeb的相关技术，进行了一个非常简陋的书城项目的搭建，目的在于学习Java后端开发的一些相关技术，无论是落后的或者是不再使用的，都具有一定的学习意义

使用到的相关技术如下：

**前端（知识掌握非常浅显，哪里有问题查哪里）**

* HTML, CSS, JavaScript（前端三剑客，页面的绘制、动画效果和简易的逻辑处理）
* JQuery （对JS的部分进行了相关的简化，使得编写代码比较容易，基本已被淘汰）
* AJAX （ajax请求，用于动态请求，如实时获得数据，可以使页面不用不停地刷新）

**后端（很多落后技术，仅作为了解）**

* JSP （后台数据处理，通过JSP附加数据后回传页面，响应请求，技术实现本质上是Servlet，很暴力）
* EL表达式、JSTL标签库 （简化了jsp中的重复输出等问题）
* Tomcat （常见的开源服务器）
* Servlet （服务端程序）
* Listener （监听器）
* Filter （过滤器）
* MySQL、JDBC、DBUtils、Druid (数据库、连接程序、简化查询过程、数据库连接池)
* Cookie、Session （存储令牌等键值对，会话中保存相关数据）
* XML、JSON （前者多用于相关的配置，后者多用于相应前端的请求，传输相应的数据） 
* Maven （进行项目的构建，包的管理，依赖导入）

虽然此时技术栈分类为前端和后端，但是并没有一个很好的界定，很难说JSP页面也叫前端，毕竟本质上只是个Servlet程序，并不是真正意义上的前端，在学习了SSM等框架后，会对前后端有一个很好的分离。

项目的结构如下所示

```shell
E:\PROGRAMDEMO\BOOKSTORE
│  BookStore.iml
│  pom.xml
│  README.md
│
├─.idea
│  │  .gitignore
│  │  compiler.xml
│  │  dataSources.local.xml
│  │  dataSources.xml
│  │  jarRepositories.xml
│  │  misc.xml
│  │  modules.xml
│  │  uiDesigner.xml
│  │  vcs.xml
│  │  webContexts.xml
│  │  workspace.xml
│  │
│  ├─artifacts
│  │      BookStore_war.xml
│  │      BookStore_war_exploded.xml
│  │
│  ├─dataSources
│  ├─inspectionProfiles
│  │      Project_Default.xml
│  │
│  └─libraries
│          Maven__commons_beanutils_commons_beanutils_1_9_4.xml
│          Maven__commons_collections_commons_collections_3_2_2.xml
│          Maven__commons_dbutils_commons_dbutils_1_7.xml
│          Maven__commons_logging_commons_logging_1_2.xml
│          Maven__com_alibaba_druid_1_2_6.xml
│          Maven__com_github_penggle_kaptcha_2_3_2.xml
│          Maven__com_google_code_gson_gson_2_8_5.xml
│          Maven__com_google_protobuf_protobuf_java_3_11_4.xml
│          Maven__com_jhlabs_filters_2_0_235_1.xml
│          Maven__javax_javaee_web_api_8_0_1.xml
│          Maven__javax_servlet_javax_servlet_api_3_1_0.xml
│          Maven__javax_servlet_jstl_1_1_2.xml
│          Maven__mysql_mysql_connector_java_8_0_25.xml
│          Maven__org_apiguardian_apiguardian_api_1_1_0.xml
│          Maven__org_junit_jupiter_junit_jupiter_api_5_7_1.xml
│          Maven__org_junit_jupiter_junit_jupiter_engine_5_7_1.xml
│          Maven__org_junit_platform_junit_platform_commons_1_7_1.xml
│          Maven__org_junit_platform_junit_platform_engine_1_7_1.xml
│          Maven__org_opentest4j_opentest4j_1_2_0.xml
│          Maven__taglibs_standard_1_1_2.xml
│
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─heavytiger
│  │  │          ├─dao
│  │  │          │  │  BaseDao.java
│  │  │          │  │  BookDao.java
│  │  │          │  │  OrderDao.java
│  │  │          │  │  OrderItemDao.java
│  │  │          │  │  UserDao.java
│  │  │          │  │
│  │  │          │  └─impl
│  │  │          │          BookDaoImpl.java
│  │  │          │          OrderDaoImpl.java
│  │  │          │          OrderItemDaoImpl.java
│  │  │          │          UserDaoImpl.java
│  │  │          │
│  │  │          ├─entity
│  │  │          │      Book.java
│  │  │          │      Cart.java
│  │  │          │      CartItem.java
│  │  │          │      Order.java
│  │  │          │      OrderItem.java
│  │  │          │      Page.java
│  │  │          │      User.java
│  │  │          │
│  │  │          ├─filter
│  │  │          │      ManagerFilter.java
│  │  │          │      TransactionFilter.java
│  │  │          │
│  │  │          ├─service
│  │  │          │  │  BookService.java
│  │  │          │  │  OrderService.java
│  │  │          │  │  UserService.java
│  │  │          │  │
│  │  │          │  └─impl
│  │  │          │          BookServiceImpl.java
│  │  │          │          OrderServiceImpl.java
│  │  │          │          UserServiceImpl.java
│  │  │          │
│  │  │          ├─utils
│  │  │          │      HexKit.java
│  │  │          │      JdbcUtils.java
│  │  │          │      WebUtils.java
│  │  │          │
│  │  │          └─web
│  │  │                  BaseServlet.java
│  │  │                  BookServlet.java
│  │  │                  CartServlet.java
│  │  │                  ClientBookServlet.java
│  │  │                  OrderServlet.java
│  │  │                  UserServlet.java
│  │  │
│  │  ├─resources
│  │  │  └─META-INF
│  │  │          beans.xml
│  │  │          jdbc.properties
│  │  │          persistence.xml
│  │  │
│  │  └─webapp
│  │      │  index.jsp
│  │      │
│  │      ├─pages
│  │      │  ├─cart
│  │      │  │      cart.jsp
│  │      │  │      checkout.jsp
│  │      │  │
│  │      │  ├─client
│  │      │  │      index.jsp
│  │      │  │
│  │      │  ├─common
│  │      │  │      foot.jsp
│  │      │  │      head.jsp
│  │      │  │      login_success_menu.jsp
│  │      │  │      manager_menu.jsp
│  │      │  │      page_nav.jsp
│  │      │  │
│  │      │  ├─error
│  │      │  │      error404.jsp
│  │      │  │      error500.jsp
│  │      │  │
│  │      │  ├─manager
│  │      │  │      book_edit.jsp
│  │      │  │      book_manager.jsp
│  │      │  │      manager.jsp
│  │      │  │      order_manager.jsp
│  │      │  │
│  │      │  ├─order
│  │      │  │      order.jsp
│  │      │  │
│  │      │  └─user
│  │      │          login.jsp
│  │      │          login_success.jsp
│  │      │          regist.jsp
│  │      │          regist_success.jsp
│  │      │
│  │      ├─static
│  │      │  │  jquery-1.7.2.js
│  │      │  │
│  │      │  ├─css
│  │      │  │      style.css
│  │      │  │
│  │      │  └─img
│  │      │          code.bmp
│  │      │          default.jpg
│  │      │          logo.gif
│  │      │          pwd-icons-new.png
│  │      │
│  │      └─WEB-INF
│  │              web.xml
│  │
│  └─test
│      ├─java
│      │  └─com
│      │      └─heavytiger
│      │          ├─dao
│      │          │  └─impl
│      │          │          BookDaoImplTest.java
│      │          │          OrderItemDaoImplTest.java
│      │          │          UserDaoImplTest.java
│      │          │
│      │          ├─service
│      │          │  └─impl
│      │          │          BookServiceImplTest.java
│      │          │          UserServiceImplTest.java
│      │          │
│      │          └─utils
│      │                  JdbcUtilsTest.java
│      │
│      └─resources
└─target
    ├─BookStore-1.0-SNAPSHOT
    │  │  index.jsp
    │  │
    │  ├─META-INF
    │  │      MANIFEST.MF
    │  │
    │  ├─pages
    │  │  ├─cart
    │  │  │      cart.jsp
    │  │  │      checkout.jsp
    │  │  │
    │  │  ├─client
    │  │  │      index.jsp
    │  │  │
    │  │  ├─common
    │  │  │      foot.jsp
    │  │  │      head.jsp
    │  │  │      login_success_menu.jsp
    │  │  │      manager_menu.jsp
    │  │  │      page_nav.jsp
    │  │  │
    │  │  ├─manager
    │  │  │      book_edit.jsp
    │  │  │      book_manager.jsp
    │  │  │      manager.jsp
    │  │  │      order_manager.jsp
    │  │  │
    │  │  ├─order
    │  │  │      order.jsp
    │  │  │
    │  │  └─user
    │  │          login.jsp
    │  │          login_success.jsp
    │  │          regist.jsp
    │  │          regist_success.jsp
    │  │
    │  ├─static
    │  │  │  jquery-1.7.2.js
    │  │  │
    │  │  ├─css
    │  │  │      style.css
    │  │  │
    │  │  └─img
    │  │          code.bmp
    │  │          default.jpg
    │  │          logo.gif
    │  │          pwd-icons-new.png
    │  │
    │  └─WEB-INF
    │      │  web.xml
    │      │
    │      ├─classes
    │      │  ├─com
    │      │  │  └─heavytiger
    │      │  │      ├─dao
    │      │  │      │  │  BaseDao.class
    │      │  │      │  │  BookDao.class
    │      │  │      │  │  OrderDao.class
    │      │  │      │  │  OrderItemDao.class
    │      │  │      │  │  UserDao.class
    │      │  │      │  │
    │      │  │      │  └─impl
    │      │  │      │          BookDaoImpl.class
    │      │  │      │          OrderDaoImpl.class
    │      │  │      │          OrderItemDaoImpl.class
    │      │  │      │          UserDaoImpl.class
    │      │  │      │
    │      │  │      ├─entity
    │      │  │      │      Book.class
    │      │  │      │      Cart.class
    │      │  │      │      CartItem.class
    │      │  │      │      Order.class
    │      │  │      │      OrderItem.class
    │      │  │      │      Page.class
    │      │  │      │      User.class
    │      │  │      │
    │      │  │      ├─filter
    │      │  │      │      ManagerFilter.class
    │      │  │      │      TransactionFilter.class
    │      │  │      │
    │      │  │      ├─service
    │      │  │      │  │  BookService.class
    │      │  │      │  │  OrderService.class
    │      │  │      │  │  UserService.class
    │      │  │      │  │
    │      │  │      │  └─impl
    │      │  │      │          BookServiceImpl.class
    │      │  │      │          OrderServiceImpl.class
    │      │  │      │          UserServiceImpl.class
    │      │  │      │
    │      │  │      ├─utils
    │      │  │      │      HexKit.class
    │      │  │      │      JdbcUtils.class
    │      │  │      │      WebUtils.class
    │      │  │      │
    │      │  │      └─web
    │      │  │              BaseServlet.class
    │      │  │              BookServlet.class
    │      │  │              CartServlet.class
    │      │  │              ClientBookServlet.class
    │      │  │              OrderServlet.class
    │      │  │              UserServlet.class
    │      │  │
    │      │  └─META-INF
    │      │          beans.xml
    │      │          jdbc.properties
    │      │          persistence.xml
    │      │
    │      └─lib
    │              commons-beanutils-1.9.4.jar
    │              commons-collections-3.2.2.jar
    │              commons-dbutils-1.7.jar
    │              commons-logging-1.2.jar
    │              druid-1.2.6.jar
    │              filters-2.0.235-1.jar
    │              javax.servlet-api-3.1.0.jar
    │              jstl-1.1.2.jar
    │              kaptcha-2.3.2.jar
    │              mysql-connector-java-8.0.25.jar
    │              protobuf-java-3.11.4.jar
    │              standard-1.1.2.jar
    │
    ├─classes
    │  ├─com
    │  │  └─heavytiger
    │  │      ├─dao
    │  │      │  │  BaseDao.class
    │  │      │  │  BookDao.class
    │  │      │  │  OrderDao.class
    │  │      │  │  OrderItemDao.class
    │  │      │  │  UserDao.class
    │  │      │  │
    │  │      │  └─impl
    │  │      │          BookDaoImpl.class
    │  │      │          OrderDaoImpl.class
    │  │      │          OrderItemDaoImpl.class
    │  │      │          UserDaoImpl.class
    │  │      │
    │  │      ├─entity
    │  │      │      Book.class
    │  │      │      Cart.class
    │  │      │      CartItem.class
    │  │      │      Order.class
    │  │      │      OrderItem.class
    │  │      │      Page.class
    │  │      │      User.class
    │  │      │
    │  │      ├─filter
    │  │      │      ManagerFilter.class
    │  │      │      TransactionFilter.class
    │  │      │
    │  │      ├─service
    │  │      │  │  BookService.class
    │  │      │  │  OrderService.class
    │  │      │  │  UserService.class
    │  │      │  │
    │  │      │  └─impl
    │  │      │          BookServiceImpl.class
    │  │      │          OrderServiceImpl.class
    │  │      │          UserServiceImpl.class
    │  │      │
    │  │      ├─utils
    │  │      │      HexKit.class
    │  │      │      JdbcUtils.class
    │  │      │      WebUtils.class
    │  │      │
    │  │      └─web
    │  │              BaseServlet.class
    │  │              BookServlet.class
    │  │              CartServlet.class
    │  │              ClientBookServlet.class
    │  │              OrderServlet.class
    │  │              UserServlet.class
    │  │
    │  └─META-INF
    │          beans.xml
    │          jdbc.properties
    │          persistence.xml
    │
    ├─generated-sources
    │  └─annotations
    ├─generated-test-sources
    │  └─test-annotations
    └─test-classes
        └─com
            └─heavytiger
                ├─dao
                │  └─impl
                │          BookDaoImplTest.class
                │          OrderItemDaoImplTest.class
                │          UserDaoImplTest.class
                │
                ├─service
                │  └─impl
                │          BookServiceImplTest.class
                │          UserServiceImplTest.class
                │
                └─utils
                        JdbcUtilsTest.class

```