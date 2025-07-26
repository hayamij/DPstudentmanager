-- Create database
CREATE DATABASE test;
GO

-- Use the database
USE test;
GO

-- Create Student Table
CREATE TABLE student (
    id NVARCHAR(10) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    major NVARCHAR(20) NOT NULL,
    javaScore DECIMAL(4,2) NULL,
    htmlScore DECIMAL(4,2) NULL,
    cssScore DECIMAL(4,2) NULL,
    marketing DECIMAL(4,2) NULL,
    salesScore DECIMAL(4,2) NULL
);

-- Insert Sample Data (20 students)
-- Software Students (12 students)
INSERT INTO student (id, name, birthday, major, javaScore, htmlScore, cssScore, marketing, salesScore) VALUES
('SW001', N'Nguyễn Văn An', '2002-01-15', 'Software', 8.5, 7.8, 8.0, NULL, NULL),
('SW002', N'Trần Thị Bình', '2002-03-22', 'Software', 9.0, 8.5, 8.8, NULL, NULL),
('SW003', N'Lê Văn Cường', '2001-12-10', 'Software', 7.2, 6.8, 7.5, NULL, NULL),
('SW004', N'Phạm Thị Dung', '2002-05-08', 'Software', 8.8, 8.2, 8.5, NULL, NULL),
('SW005', N'Hoàng Văn Em', '2001-09-30', 'Software', 6.5, 7.0, 6.8, NULL, NULL),
('SW006', N'Vũ Thị Fany', '2002-07-14', 'Software', 9.2, 9.0, 9.1, NULL, NULL),
('SW007', N'Đỗ Văn Giang', '2001-11-25', 'Software', 7.8, 7.5, 8.2, NULL, NULL),
('SW008', N'Bùi Thị Hà', '2002-02-18', 'Software', 8.0, 7.8, 7.9, NULL, NULL),
('SW009', N'Ngô Văn Inh', '2001-08-12', 'Software', 6.8, 6.5, 7.2, NULL, NULL),
('SW010', N'Lý Thị Kiều', '2002-04-06', 'Software', 8.9, 8.7, 8.8, NULL, NULL),
('SW011', N'Cao Văn Long', '2001-10-20', 'Software', 7.5, 7.2, 7.8, NULL, NULL),
('SW012', N'Phan Thị Mai', '2002-06-03', 'Software', 9.5, 9.2, 9.3, NULL, NULL),

-- Economics Students (8 students)
('EC001', N'Đinh Văn Nam', '2002-01-28', 'Economics', NULL, NULL, NULL, 8.2, 7.8),
('EC002', N'Tô Thị Oanh', '2001-12-15', 'Economics', NULL, NULL, NULL, 9.0, 8.5),
('EC003', N'Lưu Văn Phúc', '2002-03-10', 'Economics', NULL, NULL, NULL, 7.5, 7.2),
('EC004', N'Võ Thị Quỳnh', '2001-11-08', 'Economics', NULL, NULL, NULL, 8.8, 8.3),
('EC005', N'Dương Văn Rạng', '2002-05-22', 'Economics', NULL, NULL, NULL, 6.8, 7.0),
('EC006', N'Hồ Thị Sương', '2001-09-14', 'Economics', NULL, NULL, NULL, 9.2, 8.9),
('EC007', N'Mai Văn Tài', '2002-07-30', 'Economics', NULL, NULL, NULL, 7.8, 7.5),
('EC008', N'Chu Thị Uyên', '2001-04-18', 'Economics', NULL, NULL, NULL, 8.5, 8.0);

-- Query to verify data
SELECT * FROM student ORDER BY major, id; 