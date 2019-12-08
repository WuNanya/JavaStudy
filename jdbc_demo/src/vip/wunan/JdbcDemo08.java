package vip.wunan;

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
            System.out.println(sql);
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
