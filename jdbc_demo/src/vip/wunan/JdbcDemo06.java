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
