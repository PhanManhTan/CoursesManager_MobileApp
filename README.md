# Rule Base Làm Việc

---

## 1. Git Workflow

### Branch
main        ← production (chỉ merge khi hoàn thiện)
develop     ← tích hợp chung tính năng mới
feature/*   ← mỗi thành viên làm việc ở branch này

### Quy trình
```bash
# Bắt đầu task mới
git checkout develop
git pull origin develop
git checkout -b feature/ten-tinh-nang

# Làm xong thì commit
git add .
git commit -m "feat: mô tả ngắn gọn"

# Push lên remote
git push origin feature/ten-tinh-nang
Sau khi push → tạo Pull Request trên GitHub:
feature/* → develop
Khi chuẩn bị release → tạo Pull Request:
develop → main
Lưu ý: Mọi merge vào develop và main đều phải qua Pull Request, không merge trực tiếp.
Đặt tên branch
feature/login-screen
feature/user-profile
bugfix/crash-on-startup
Commit message
feat: add login screen
fix: fix crash when user is null
refactor: move adapter to separate class
style: reformat LoginActivity

2. Đặt Tên Class & File

Đặt tên File XML (Layout)

Java class: LoginActivity.java
File XML: login_activity.xml

Quy tắc chung:

Activity → login_activity.xml
Fragment → home_fragment.xml
Item RecyclerView → item_user.xml
Dialog → dialog_confirm.xml
