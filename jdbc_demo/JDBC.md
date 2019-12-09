### JDCB快速入门
- **概念**: Java Database Connectivity Java数据库连接 java语言操作数据库
- **JDBC本质**:官方定义的一套操作所有关系型数据库的规则，即接口，各厂商去实现这个接口，提供数据库jar包，可以使用这套接口，真正执行的代码时驱动jar包中的实现类
- 快速入门：
    - 步骤：
    1. 导入驱动jar包
    2. 注册驱动
    3. 获取数据库连接对象Connection
    4. 定义sql语句
    5. 获取执行sql语句的对象 Statment
    6. 执行sql，接受返回结果
    7. 处理结果
    8. 释放资源
```
package vip.wunan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.导入jar包
        //2.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //3.获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "990216");
        //4.定义sql语句
        String sql = "update tb11 set age = 100 where id = 1";
        //5.获取执行sql的对象
        Statement stmt = conn.createStatement();
        //6.执行sql
        int count = stmt.executeUpdate(sql);
        //7.处理结果
        System.out.println(count);
        //8.释放资源
        stmt.close();
        conn.close();
    }
}

```

### 详解各个对象
1. DriverManager：驱动管理对象
    - 功能：
        1. 注册驱动：告诉程序该使用哪一个数据jar包
        2. 获取数据库连接
            - 方法：getConnection
            - 参数：
                - url：指定连接的路径
                    - 细节：若连接的是本机的mysql服务器，并且mysql服务器默认的端口号是3306，则url可以简写为：jdbc:mysql:/// 数据库名称
                - user：用户名
                - password：密码
2. Connection：数据库连接对象
    1. 获取执行sql的对象
        - Statement createStatement()
        - PreparedStatement preparedstatement(String sql)
    2. 管理事务
        - 开启事务:setAutoCommit(boolean autoCommit):调用该方法设置参数为false，即开启事务
        - 管理事务:commit()
        - 关闭事务:rollback
3. Statement：执行sql的对象
    1. 执行sql
        - execute(String sql):可以执行任意的sql 了解
        - executUpdate(String sql):执行DML(insert,update,delete),DDL(create,alter,drop)语句
        - ResultSet executeQuery(String sql):执行DQL(select语句)
4. ResultSet：结果集对象
5. PreparedStatement：执行sql对象 更加强大

### 练习
1. 添加一条记录
```
package vip.wunan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
*添加一条记录
*/
public class JdbcDemo02 {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn =  null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.定义sql
            String sql = "insert into tb11() values(null,'liyue',3)";
            //3.获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "990216");
            //4.获取执行sql的对象statement
            stmt = conn.createStatement();
            //5.执行sql
            int count = stmt.executeUpdate(sql);   //返回英希昂的行数
            //6.处理结果
            System.out.println(count);
            if(count>0){
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //要避免stmt空指针异常，如conn的时候就异常了
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

```
2. 修改记录
```
package vip.wunan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
修改记录
 */
public class JdbcDemo03 {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.定义sql
            String sql = "update tb11 set age = 8 where name = 'sunan'";
            //3.建立连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///db1","root","990216");
            //4.创建执行sql对象
            stmt = conn.createStatement();
            //5.执行sql语句
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
            if(count > 0){
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

```
3. 删除一条记录
```
package vip.wunan;

import java.sql.*;

public class JdbcDemo04 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.定义sql
            String sql = "delete from tb11 where name = 'root'";
            //3.建立connection
            conn = DriverManager.getConnection("jdbc:mysql:///db1","root","990216");
            //4.建立执行sql的对象
            stmt = conn.createStatement();
            //5.执行sql
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
            if(count > 0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

```


### ResultSet对象详解
- ResultSet：结果集对象，封装查询结果
    - next():游标向下移动一行
    - getXxx(参数):获取数据
        - Xxx代表数据类型 如:getInt()
        - 参数：
            - int：代表的列的编号，编号从1开始 如：getInt(1)
            - String：代表列的名称 如果：getInt("age")
```
import java.sql.*;

public class JdbcDemo05 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.定义sql
            String sql = "select * from tb11";
            //3.建立connection
            conn = DriverManager.getConnection("jdbc:mysql:///db1","root","990216");
            //4.建立执行sql的对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.输出结果
            while(rs.next()){  //将光标指到第一行数据
            int id = rs.getInt(1);
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println(id + "---" + name + "---" + age);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

```
            
### 练习
> 定义一个方法，查询emp表的数据将其封装为对象，然后装在集合中。
> 1. 定义Emp类
> 2. 定义方法public List<Emp> findAll(){}
> 3. 实现方法select * from emp;

```
package vip.wunan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo06 {
    public static void main(String[] args) {
        List<Student> list = new JdbcDemo06().findAll();
        System.out.println(list);
    }

    public List<Student> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.定义sql语句
            String sql = "select * from tb11";
            //3.创建数据库连接
            conn = DriverManager.getConnection("jdbc:mysql:///db1","root","990216");
            //4.创建执行sql的对象
            stmt = conn.createStatement();
            //5.执行sql语句
            rs = stmt.executeQuery(sql);
            //6.创建一个集合对象 用于复用
            Student stu = null;
            List<Student> list = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int age = rs.getInt("age");
                stu = new Student();
                stu.setId(id);
                stu.setAge(age);
                stu.setName(name);
                list.add(stu);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }
}

```

### 抽取jdbc工具类 JDBCUtils
 - 目的：简化书写
 - 分析：
    - 注册驱动抽取
    - 抽取一个方法获取对象
        - 不想传递参数，保证工具类的通用性
        - 用配置文件解决
            - jdbc.properties
    - 抽取一个方法释放资源
```
package vip.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    static{
        try {
            //读取资源文件
            //1.创建Properties集合类
            Properties pro = new Properties();
            //获取src路径下的文件的方式 ---> ClassLoader类加载器
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            System.out.println(res);
            String path = res.getPath();
            System.out.println(path);
            //2.加载文件
            pro.load(new FileReader(path));
            //3.获取数据
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //4.注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*
    获取连接
    @return连接对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    /*
    *释放资源
    * @param stmt
    * @param conn
    */
    public static void close(Statement stmt,Connection conn){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    *释放资源
    * @param rs
    * @param stmt
    * @param conn
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

```

#### 运用工具类连接
```
package vip.wunan;

import vip.util.JDBCUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo07 {
    public static void main(String[] args) {
        List<Student> list = new JdbcDemo07().findAll();
        System.out.println(list);
    }

    public List<Student> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
//            //1.注册驱动
//            Class.forName("com.mysql.jdbc.Driver");
            conn = JDBCUtils.getConnection();
            //2.定义sql语句
            String sql = "select * from tb11";
//            //3.创建数据库连接
//            conn = DriverManager.getConnection("jdbc:mysql:///db1","root","990216");
            //4.创建执行sql的对象
            stmt = conn.createStatement();
            //5.执行sql语句
            rs = stmt.executeQuery(sql);
            //6.创建一个集合对象 用于复用
            Student stu = null;
            List<Student> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int age = rs.getInt("age");
                stu = new Student();
                stu.setId(id);
                stu.setAge(age);
                stu.setName(name);
                list.add(stu);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }
        return null;
    }
}

```

### 练习
- 需求
    - 通过键盘录入用户名和密码
    - 判断用户是否登录成功
- 实现步骤：
    - 创建一个数据库表 
```
package vip.wunan;

import javafx.application.Preloader;
import vip.util.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
练习
- 需求
    - 通过键盘录入用户名和密码
    - 判断用户是否登录成功
- 实现步骤：
    - 创建一个数据库表
 */
public class JdbcDemo08 {

    public static void main(String[] args) {
        //1.键盘录入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();
        System.out.println("请输入密码：");
        String password = sc.next();
        //2.调用方法
        boolean flag = new JdbcDemo08().login(username,password);
        if(flag){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }

    }
    /*
    登陆方法
     */
    public boolean login(String username, String password){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        if(username == null || password == null){
            return false;
        }
        try {
            //连接数据库,判断是否成功
            conn = JDBCUtils.getConnection();
            //定义sql语句
            String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
            //获取执行sql的对象
            stmt = conn.createStatement();
            //执行查询
            rs = stmt.executeQuery(sql);
            //判断
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,stmt,conn);
        }
        return false;
    }
}

```

### PreparedStatment对象
> 执行sql的对象

1. 解决SQL注入问题
2. 预编译的SQL: 参数使用?作为占位符
3. 步骤
    - 定义sql语句
    ```
    select  * from user where username = ? and password = ?;
    ```
    - 获取执行sql语句的对象PreparedStatement
    - 给 ？ 赋值
        - 方法：set(参数1，参数2)
        - 参数1:?的位置编号，从1开始
        - 参数2:?的值
```
            //定义sql语句
            String sql = "select * from user where username = ? and password = ?";
            //获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
```

### 修改后的登录案例
```
package vip.wunan;

import vip.util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

/*
练习
- 需求
    - 通过键盘录入用户名和密码
    - 判断用户是否登录成功
- 实现步骤：
    - 创建一个数据库表
 */
public class JdbcDemo09 {

    public static void main(String[] args) {
        //1.键盘录入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();
        System.out.println("请输入密码：");
        String password = sc.next();
        //2.调用方法
        boolean flag = new JdbcDemo09().login2(username,password);
        if(flag){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }

    }
    /*
    登陆方法
     */
    public boolean login2(String username, String password){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if(username == null || password == null){
            return false;
        }
        try {
            //连接数据库,判断是否成功
            conn = JDBCUtils.getConnection();
            //定义sql语句
            String sql = "select * from user where username = ? and password = ?";
            //获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            //执行查询
            rs = pstmt.executeQuery();
            //判断
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return false;
    }
}

```
