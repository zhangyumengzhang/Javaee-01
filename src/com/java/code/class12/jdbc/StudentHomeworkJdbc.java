package com.java.code.class12.jdbc;

import com.java.code.class12.Model.StudentHomework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentHomeworkJdbc
 *
 * @author wangkm
 * @date 2020-03-05
 * @since 0.0.1
 */
public class StudentHomeworkJdbc {

    public static void main(String[] args) {
        List<StudentHomework> list = selectAll();

        for (StudentHomework sh : list){
            System.out.println(sh.getHomeworkContent());
        }
    }

    public static void addStudentHomework(StudentHomework sh){

        /**
         *
         */

    }

    public static List<StudentHomework> selectAll(){

        String url ="jdbc:mysql://localhost:3306/javaee?serverTimezone=UTC&useSSL=false";
        String username = "root";
        String password = "123456";

        String driverName = "com.mysql.cj.jdbc.Driver";

        String sqlString = "SELECT * FROM student_homework";

        try {
            // 加载驱动
            Class.forName(driverName);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<StudentHomework> list = new ArrayList<>();
        try(Connection connection =  DriverManager.getConnection(url,username,password)) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        StudentHomework sh = new StudentHomework();
                        sh.setId(resultSet.getInt("id"));
                        sh.setStudentId(resultSet.getInt("student_id"));
                        sh.setHomeworkId(resultSet.getInt("homework_id"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        sh.setUpdateTime(resultSet.getTimestamp("update_time"));
                        list.add(sh);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
