package com.example.myapplication.utils;

import com.example.myapplication.models.*;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    private static List<Course> cartItems = new ArrayList<>();
    private static List<Enrollment> enrollments = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Report> reports = new ArrayList<>();
    private static List<Category> categories = new ArrayList<>();

    static {
        // --- Users ---
        User admin = new User("admin_id", "System Admin", "admin@gmail.com", "123", "https://i.pravatar.cc/150?u=admin", "Admin", "Full system administrator.", "2023-01-01");
        admin.setStatus("active");
        users.add(admin);

        User instructor = new User("instr_id", "John Instructor", "instructor@gmail.com", "123", "https://i.pravatar.cc/150?u=instr", "Instructor", "Mobile development expert with 10 years of experience.", "2023-01-01");
        instructor.setStatus("active");
        users.add(instructor);

        User student = new User("student_id", "Demo Student", "student@gmail.com", "123", "https://i.pravatar.cc/150?u=student", "Student", "Passionate about technology and learning.", "2023-01-01");
        student.setStatus("active");
        users.add(student);

        User s1 = new User("s1", "Alex Nguyen", "a@test.com", "123", "https://i.pravatar.cc/150?u=a", "Student", "3rd year IT student.", "2023-02-01");
        s1.setStatus("active");
        users.add(s1);

        // --- Courses ---
        courses.add(new Course("c1", "instr_id", "Android Masterclass 2024", "Hands-on Android course from basics to advanced.", null, 99.99, 79.99, "published", "cat1", "2023-06-01"));
        courses.add(new Course("c2", "instr_id", "UI/UX Design", "Modern UI design with Figma.", null, 89.99, 69.99, "published", "cat3", "2023-07-01"));
        courses.add(new Course("c3", "instr_id", "NodeJS Backend", "Build high-performance servers.", null, 129.99, 109.99, "pending", "cat1", "2023-08-01"));
        courses.add(new Course("c4", "admin_id", "System Administration", "Advanced Linux system administration.", null, 150.00, 120.00, "published", "cat1", "2023-09-01"));

        // --- Enrollments ---
        enrollments.add(new Enrollment("e1", "student_id", "c1", "2023-06-15", 79.99, "active"));

        // --- Reports for Admin ---
        reports.add(new Report("rep1", "Course video content has errors", "c1", "student_id", "2023-11-01", "pending"));
        reports.add(new Report("rep2", "Attached files cannot be downloaded", "c2", "s1", "2023-11-05", "resolved"));

        // --- Categories ---
        categories.add(new Category("cat1", "Programming", "programming", null, "https://via.placeholder.com/100?text=Code", "2023-01-01"));
        categories.add(new Category("cat2", "Business", "business", null, "https://via.placeholder.com/100?text=Business", "2023-01-01"));
        categories.add(new Category("cat3", "Design", "design", null, "https://via.placeholder.com/100?text=Design", "2023-01-01"));
        categories.add(new Category("cat4", "Marketing", "marketing", null, "https://via.placeholder.com/100?text=Marketing", "2023-01-01"));

        // --- Initial Cart ---
        cartItems.add(courses.get(1));
    }

    public static List<User> getUsers() { return users; }
    public static List<Course> getCourses() { return courses; }
    public static List<Enrollment> getEnrollments() { return enrollments; }
    public static List<Report> getReports() { return reports; }
    public static List<Course> getCartItems() { return cartItems; }
    public static List<Category> getCategories() { return categories; }

    public static Course getCourseById(String id) {
        for (Course c : courses) if (c.getId().equals(id)) return c;
        return null;
    }

    public static void addToCart(Course course) {
        for (Course c : cartItems) if (c.getId().equals(course.getId())) return;
        cartItems.add(course);
    }
    public static void removeFromCart(String id) { cartItems.removeIf(c -> c.getId().equals(id)); }
    public static void clearCart() { cartItems.clear(); }

    // Bypass payment logic: Chuyển giỏ hàng sang danh sách đã học
    public static void processPayment(String userId) {
        for (Course c : cartItems) {
            enrollments.add(new Enrollment("e_" + System.currentTimeMillis(), userId, c.getId(), "2023-12-01", c.getPrice(), "active"));
        }
        clearCart();
    }

    public static List<Course> getCoursesByInstructor(String instructorId) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses) if (c.getInstructorId().equals(instructorId)) result.add(c);
        return result;
    }

    public static User getUserById(String id) {
        for (User u : users) if (u.getId().equals(id)) return u;
        return null;
    }
    
    public static User getUserByEmail(String email) {
        for (User u : users) if (u.getEmail().equalsIgnoreCase(email)) return u;
        return null;
    }
}
