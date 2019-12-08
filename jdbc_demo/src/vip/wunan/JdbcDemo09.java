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
