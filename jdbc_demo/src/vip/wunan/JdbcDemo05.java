package vip.wunan;

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
            rs.next();  //将光标指到第一行数据
            int id = rs.getInt(1);
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println(id + "---" + name + "---" + age);
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
