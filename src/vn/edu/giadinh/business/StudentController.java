package vn.edu.giadinh.business;

import java.sql.SQLException;
import java.text.ParseException;

import vn.edu.giadinh.presentation.StudentListViewUI;

/**
 * Controller trong mẫu MVC
 * Điều phối giữa View và Business Logic
 * Không vi phạm quy tắc 3-tier architecture
 */
public class StudentController {
    
    // Dependencies
    private StudentListViewUI view;
    private StudentListViewUseCase listViewUseCase;
    private AddStudentUseCase addStudentUseCase;
    
    public StudentController(StudentListViewUI view, 
                           StudentListViewUseCase listViewUseCase, 
                           AddStudentUseCase addStudentUseCase) {
        this.view = view;
        this.listViewUseCase = listViewUseCase;
        this.addStudentUseCase = addStudentUseCase;
        
        // Thiết lập controller cho view
        this.view.setController(this);
    }
    
    /**
     * Xử lý yêu cầu hiển thị danh sách sinh viên
     */
    public void handleViewStudentList() {
        try {
            // Gọi business logic
            StudentViewModel viewModel = listViewUseCase.execute();
            
            // Cập nhật view
            view.showList(viewModel);
            
        } catch (SQLException | ParseException e) {
            view.showError("Lỗi khi tải danh sách sinh viên: " + e.getMessage());
        }
    }
    
    /**
     * Xử lý yêu cầu thêm sinh viên mới
     */
    public void handleAddStudent(Student student) {
        try {
            // Gọi business logic
            boolean success = addStudentUseCase.execute(student);
            
            if (success) {
                // Refresh danh sách sau khi thêm thành công
                handleViewStudentList();
                view.showSuccess("Thêm sinh viên thành công!");
            } else {
                view.showError("Không thể thêm sinh viên!");
            }
            
        } catch (Exception e) {
            view.showError("Lỗi khi thêm sinh viên: " + e.getMessage());
        }
    }
    
    /**
     * Xử lý yêu cầu tìm kiếm sinh viên
     */
    public void handleSearchStudent(String keyword) {
        // TODO: Implement search functionality
        // Có thể tạo SearchStudentUseCase mới
    }
    
    /**
     * Xử lý yêu cầu xóa sinh viên
     */
    public void handleDeleteStudent(String studentId) {
        // TODO: Implement delete functionality
        // Có thể tạo DeleteStudentUseCase mới
    }
    
    /**
     * Khởi tạo controller và load dữ liệu ban đầu
     */
    public void initialize() {
        handleViewStudentList();
    }
}
