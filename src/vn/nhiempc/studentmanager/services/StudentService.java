package vn.nhiempc.studentmanager.services;

import vn.nhiempc.studentmanager.models.DepartmentEntity;
import vn.nhiempc.studentmanager.models.StudentEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private Connection con;

    public StudentService() {
    }

    public StudentService(Connection con) {
        this.con = con;
    }
    private List<StudentEntity> queryDatabase(String sql){
        List<StudentEntity> studentEntities = new ArrayList<>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                StudentEntity studentEntity = new StudentEntity(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getLong(8),
                        rs.getFloat(9)
                );
                studentEntities.add(studentEntity);
            }
        }catch (Exception e) {
            System.out.println("Error when query!" + e);
        }
        return studentEntities;
    }
    public List<StudentEntity> averageScore(){
        String sql = "UPDATE students SET AverageScore = (SELECT SUM(total)/(SELECT SUM(Credits) FROM (SELECT DISTINCT results.CourseID,Credits FROM courses,results WHERE StudentID = students.StudentID AND results.CourseID = courses.CourseID) AS total_credit) FROM (SELECT (MAX(Mark)*(SELECT Credits FROM courses WHERE CourseID = results.CourseID)) AS total FROM results WHERE StudentID = students.StudentID GROUP BY results.CourseID) AS total_score)";
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("Cập nhật thất bại! "+e);
        }
        String sql_select = "SELECT * FROM students";
        List<StudentEntity> update_student_score = queryDatabase(sql_select);
        return update_student_score;
    }
}
