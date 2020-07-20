package vn.nhiempc.studentmanager;

import vn.nhiempc.studentmanager.models.DepartmentEntity;
import vn.nhiempc.studentmanager.models.StudentEntity;
import vn.nhiempc.studentmanager.services.DepartmentService;
import vn.nhiempc.studentmanager.services.StudentService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/manage_student", "root", "123456");
            System.out.println("SQL Connection to database established!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            return;
        }
        DepartmentService departmentService = new DepartmentService(connection);
        List<DepartmentEntity> updateStudentNum = departmentService.updateStudentQuantity();
        System.out.println("Danh sách Department:");
        if(updateStudentNum==null||updateStudentNum.isEmpty()){
            System.out.println("Không có dữ liệu!");
        }else{
            for(DepartmentEntity result: updateStudentNum){
                System.out.println("Department ID: "+result.getDeptID()+" | Tên: "+result.getName()+" | Số lượng sinh viên: "+result.getNoOfStudents());
            }
        }
        System.out.println();
        StudentService studentService = new StudentService(connection);
        List<StudentEntity> updateStudentScore = studentService.averageScore();
        System.out.println("Điểm trung bình:");
        if(updateStudentScore==null||updateStudentScore.isEmpty()){
            System.out.println("Không có dữ liệu!");
        }else{
            for(StudentEntity result: updateStudentScore){
                System.out.println("Student ID: "+result.getStudentID()+" | Tên: "+result.getFirstName()+" | Điểm Trung Bình: "+result.getAverageScore());
            }
        }
    }
}
