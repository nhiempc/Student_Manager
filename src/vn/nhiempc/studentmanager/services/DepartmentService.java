package vn.nhiempc.studentmanager.services;

import vn.nhiempc.studentmanager.models.DepartmentEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    private Connection con;

    public DepartmentService() {
    }

    public DepartmentService(Connection con) {
        this.con = con;
    }
    private List<DepartmentEntity> queryDatabase(String sql){
        List<DepartmentEntity> departmentEntities = new ArrayList<>();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                DepartmentEntity departmentEntity = new DepartmentEntity(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                departmentEntities.add(departmentEntity);
            }
        }catch (Exception e) {
            System.out.println("Error when query!" + e);
        }
        return departmentEntities;
    }
    public List<DepartmentEntity> updateStudentQuantity(){
        String sql = "UPDATE departments SET NoOfStudents = (SELECT COUNT(DeptID) FROM students WHERE DeptID = departments.DeptID)";
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        }catch (Exception e){
            System.out.println("Cập nhật thất bại! "+e);
        }
        String sql_select = "SELECT * FROM departments";
        List<DepartmentEntity> update_student_quantity = queryDatabase(sql_select);
        return update_student_quantity;
    }
}
