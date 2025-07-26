package vn.edu.giadinh.business;

import vn.edu.giadinh.persistence.StudentListViewDAO;

public class AddStudentUseCase {
    private StudentListViewDAO studentDAO;
    
    public AddStudentUseCase(StudentListViewDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    
    public boolean execute(Student student) {
        try {
            // Business validation
            if (student == null) {
                throw new IllegalArgumentException("Student không được null");
            }
            
            if (student.getId() == null || student.getId().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã sinh viên không được trống");
            }
            
            if (student.getName() == null || student.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Tên sinh viên không được trống");
            }
            
            if (student.getBirthDate() == null) {
                throw new IllegalArgumentException("Ngày sinh không được trống");
            }
            
            // Call DAO to insert
            return studentDAO.insert(student);
            
        } catch (Exception e) {
            System.err.println("Error in AddStudentUseCase: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
