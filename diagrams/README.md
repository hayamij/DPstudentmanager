# 📊 Student Manager - UML Diagrams Collection

Tổng hợp các sơ đồ UML cho dự án Student Manager System với kiến trúc 3 tầng.

## 🎯 Cách sử dụng PlantUML trong VS Code:

1. **Đảm bảo có extension**: PlantUML (jebbs.plantuml)
2. **Mở file .puml** bất kỳ 
3. **Preview**: `Ctrl+Shift+P` → "PlantUML: Preview Current Diagram"
4. **Export**: `Ctrl+Shift+P` → "PlantUML: Export Current Diagram"

---

## 📋 Danh sách các sơ đồ UML:

### 1. 🏗️ [Class Diagram](./class-diagram.puml)
- **Mô tả**: Cấu trúc tĩnh của toàn bộ hệ thống
- **Bao gồm**: Tất cả classes trong 3 tầng, relationships, attributes, methods
- **Highlight**: Inheritance, Composition, Dependencies giữa các tầng

### 2. 🔄 [Sequence - Hiển thị danh sách](./sequence-list.puml)
- **Mô tả**: Luồng hiển thị danh sách sinh viên
- **Bao gồm**: User → UI → UseCase → DAO → Database
- **Use case**: Khởi động app, refresh list

### 3. ➕ [Sequence - Thêm sinh viên](./sequence-add.puml)
- **Mô tả**: Quy trình thêm sinh viên mới
- **Bao gồm**: Form input → Validation → Business logic → Database insert → UI refresh
- **Highlight**: Error handling, validation layers

### 4. 📦 [Package Diagram](./package-diagram.puml)
- **Mô tả**: Kiến trúc 3 tầng và dependencies
- **Bao gồm**: Presentation ↔ Business ↔ Persistence
- **External**: Swing, JDBC, SQL Server

### 5. 🎬 [Activity - Thêm sinh viên](./activity-add-student.puml)
- **Mô tả**: Quy trình chi tiết thêm sinh viên
- **Bao gồm**: Decision points, validation flows, error paths
- **Highlight**: Business rules, validation logic

### 6. 👥 [Use Case Diagram](./usecase-diagram.puml)
- **Mô tả**: Chức năng hệ thống và actors
- **Actors**: User, Admin
- **Use cases**: View list, Add student, Calculate GPA, etc.

### 7. 🔧 [Component Diagram](./component-diagram.puml)
- **Mô tả**: Kiến trúc component và interfaces
- **Bao gồm**: High-level components, external dependencies
- **Highlight**: Component responsibilities

### 8. 🔗 [Relationships Detail](./relationships-detail.puml)
- **Mô tả**: Chi tiết 4 loại quan hệ UML với ví dụ
- **Bao gồm**: Inheritance, Composition, Aggregation, Association
- **Highlight**: Ký hiệu, ý nghĩa, ví dụ cụ thể

### 9. 📊 [Relationships Summary](./relationships-summary.puml)
- **Mô tả**: Tóm tắt relationships trong Student Manager
- **Bao gồm**: Áp dụng 4 loại quan hệ vào hệ thống thực tế
- **Highlight**: So sánh, phân tích lifecycle

---

## 🎨 Màu sắc trong sơ đồ:

| Tầng | Màu | Ý nghĩa |
|------|-----|---------|
| 🟢 **Presentation** | `#E8F5E8` | Giao diện người dùng |
| 🔵 **Business** | `#E8E8F5` | Logic nghiệp vụ |
| 🔴 **Persistence** | `#F5E8E8` | Truy cập dữ liệu |

## 🎯 **Đặc biệt về Relationships:**

### 📐 **4 Loại Quan Hệ UML Được Sử Dụng:**

| Loại | Ký hiệu | Ý nghĩa | Ví dụ trong hệ thống |
|------|---------|---------|---------------------|
| **🔺 Inheritance** | `<\|--` | IS-A (thừa kế) | `Student ← SoftwareStudent` |
| **💎 Composition** | `*--` | PART-OF mạnh (sở hữu) | `UI *-- UseCase` |
| **💠 Aggregation** | `o--` | HAS-A yếu (sử dụng) | `UseCase o-- DAO` |
| **➡️ Association** | `-->` | USES/KNOWS (liên kết) | `Dialog → UseCase` |

### 🎓 **Để hiểu rõ Relationships:**
1. **Bắt đầu với**: `relationships-detail.puml` - Lý thuyết và ví dụ
2. **Áp dụng**: `relationships-summary.puml` - Thực tế trong hệ thống
3. **Tổng hợp**: `class-diagram.puml` - Toàn bộ relationships

---

## 📖 Thứ tự đọc sơ đồ đề xuất:

### 🥇 **Cho người mới bắt đầu:**
1. **Use Case Diagram** - Hiểu chức năng tổng quan
2. **Package Diagram** - Hiểu kiến trúc 3 tầng  
3. **Class Diagram** - Hiểu cấu trúc classes
4. **Sequence Diagrams** - Hiểu luồng hoạt động

### 🥈 **Cho developer:**
1. **Class Diagram** - Cấu trúc code
2. **Component Diagram** - Kiến trúc components
3. **Sequence Diagrams** - Interaction patterns
4. **Activity Diagram** - Business processes

### 🥉 **Cho architect:**
1. **Package Diagram** - Architectural layers
2. **Component Diagram** - System structure
3. **Class Diagram** - Detailed design
4. **Use Case Diagram** - Functional requirements

---

## 🔍 Chi tiết kỹ thuật:

### **Design Patterns được thể hiện:**
- ✅ **MVC Pattern**: Model (Student) + View (UI) + Controller (UseCase)
- ✅ **DAO Pattern**: Data access encapsulation
- ✅ **Template Method**: Student.calculateGPA() abstract
- ✅ **Dependency Injection**: Constructor injection
- ✅ **3-Tier Architecture**: Layered separation

### **Relationships trong Class Diagram:**
- 🔺 **Inheritance**: Student ← SoftwareStudent/EconomicsStudent
- 💎 **Composition**: UI contains UseCases
- ➡️ **Dependency**: UseCase → DAO
- 🔗 **Association**: Dialog → UI (parent)

---

## 💡 Tips cho việc đọc sơ đồ:

1. **Bắt đầu từ AppStudent**: Entry point của hệ thống
2. **Theo dõi data flow**: Presentation → Business → Persistence
3. **Chú ý màu sắc**: Mỗi tầng có màu riêng biệt
4. **Đọc notes**: Chứa thông tin quan trọng
5. **Zoom để xem chi tiết**: PlantUML hỗ trợ zoom tốt

---

## 🚀 Export sơ đồ:

```bash
# Export all diagrams to PNG
Ctrl+Shift+P → "PlantUML: Export Workspace Diagrams"

# Export formats: PNG, SVG, PDF, LaTeX
```

---

**📌 Lưu ý**: Các sơ đồ này được thiết kế để phục vụ mục đích học tập và documentation. Chúng phản ánh đúng cấu trúc code thực tế của dự án Student Manager.
