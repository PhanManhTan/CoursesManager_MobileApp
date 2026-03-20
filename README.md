# Rule base làm việc

---
## 1. Git Workflow

### Branch

```
main        ← production, nào hoàn thiện rồi merge vào
develop     ← tích hợp chung tính năng mới trước khi merge vào main
feature/*   ← mỗi thành viên làm việc ở đây 
```

### Quy trình 

```bash
# Bắt đầu task mới
git checkout develop
git pull origin develop
git checkout -b feature/ten-tinh-nang

# Làm việc xong thì commit
git add .
git commit -m "feat: mô tả ngắn gọn"

# Push lên remote
git push origin feature/ten-tinh-nang
```

Sau khi push → **tạo Pull Request** trên GitHub: `feature/*` → `develop`

Khi chuẩn bị release → **tạo Pull Request**: `develop` → `main`

> Mọi merge vào `develop` và `main` đều phải qua Pull Request, không merge trực tiếp.

### Đặt tên branch

```
feature/login-screen
feature/user-profile
bugfix/crash-on-startup
```
Viết thường, dùng dấu `-`, mô tả rõ làm gì.

### Commit message

```
feat: add login screen
fix: fix crash when user is null
refactor: move adapter to separate class
style: reformat LoginActivity
```

---

## 2. Cấu Trúc Thư Mục

```
app/
└── src/
    ├── main/
    │   ├── java/com/example/tenapp/
    │   │   │
    │   │   ├── ui/
    │   │   │   ├── auth/
    │   │   │   │   ├── LoginActivity.java
    │   │   │   │   └── RegisterActivity.java
    │   │   │   ├── home/
    │   │   │   │   └── HomeActivity.java
    │   │   │   └── profile/
    │   │   │       └── ProfileActivity.java
    │   │   │
    │   │   ├── data/
    │   │   │   ├── model/              # User.java, Product.java...
    │   │   │   ├── repository/         # UserRepository.java
    │   │   │   └── remote/             # ApiService.java (Retrofit)
    │   │   │
    │   │   ├── utils/                  #
    │   │   └── MainActivity.java
    │   │
    │   └── res/
    │       ├── drawable/               # icon, background xml
    │       ├── layout/
    │       │   ├── activity_main.xml
    │       │   ├── activity_login.xml
    │       │   └── item_user.xml
    │       ├── values/
    │       │   ├── strings.xml
    │       │   ├── colors.xml
    │       │   └── themes.xml
    │       └── mipmap-*/      
    │
    └── androidTest/                 
```
 
**Lưu ý:**
- Mỗi màn hình = 1 folder riêng trong `ui/`
- File `MainActivity.java` nằm ở root package, là entry point của app
 
---

## 3. Đặt Tên Class & File

| Loại | Cách đặt tên | Ví dụ |
|------|-------------|-------|
| Activity | `...Activity` | `LoginActivity` |
| Fragment | `...Fragment` | `HomeFragment` |
| ViewModel | `...ViewModel` | `LoginViewModel` |
| Adapter | `...Adapter` | `UserListAdapter` |
| Model | Tên danh từ | `User`, `Product` |
| Interface | `...Listener` hoặc `I...` | `OnItemClickListener` |

---

## 4. Cách Code

### Đặt tên biến & method

```java
// Class — PascalCase
public class UserProfile {}

// Method — camelCase, bắt đầu bằng động từ
public void loadData() {}
public boolean isLoggedIn() {}

// Biến — camelCase, mô tả rõ
private String userName;
private boolean isLoading;

// Hằng số — UPPER_SNAKE_CASE
public static final int MAX_RETRY = 3;
```

### Cấu trúc bên trong 1 class

```java
public class LoginActivity extends AppCompatActivity {

    // 1. Khai báo Views
    private EditText etEmail;
    private Button btnLogin;

    // 2. ViewModel
    private LoginViewModel viewModel;

    // 3. Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) { ... }

    // 4. Setup methods
    private void setupViews() { ... }
    private void observeViewModel() { ... }
}
```

### Quy tắc cơ bản

```java
// ✅ Kiểm tra null trước khi dùng
if (user != null) {
    tvName.setText(user.getName());
}

// ✅ Early return, tránh lồng if nhiều tầng
public void process(User user) {
    if (user == null) return;
    if (!user.isActive()) return;
    // logic chính ở đây
}

// ✅ Xử lý lỗi rõ ràng
try {
    // ...
} catch (Exception e) {
    Log.e(TAG, "Lỗi: " + e.getMessage());
}

// ❌ Không để lại debug log khi commit
System.out.println("test");
Log.d(TAG, "debug tạm");
```

### Đặt tên View trong XML

```xml
<TextView    android:id="@+id/tv_user_name" />
<EditText    android:id="@+id/et_email" />
<Button      android:id="@+id/btn_submit" />
<ImageView   android:id="@+id/iv_avatar" />
<RecyclerView android:id="@+id/rv_list" />
```

Pattern: `<loại_view>_<mô_tả>`
