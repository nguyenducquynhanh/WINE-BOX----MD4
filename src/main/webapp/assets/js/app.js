class App {

    static ERROR_400 = "Access Denied! Please check the data again!";
    static ERROR_401 = "Access Denied! Invalid credentials!";
    static ERROR_403 = "Access Denied! You are not authorized to perform this function!";
    static ERROR_404 = "An error occurred! Please try again later!";
    static ERROR_500 = "Data saving Failed! Please contact system administrator!";

    static SweetAlertConfirm = class {
        static showSuspendConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Are you sure to DELETE this selection?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, please DELETE this selection!',
                cancelButtonText: 'Cancel',
            })
        }
    };
    static SweetAlertConfirmBlocked = class {
        static showBlockConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Are you sure to BLOCK this account?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, please BLOCK this account!',
                cancelButtonText: 'Cancel',
            })
        }

        static showUnlockConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Are you sure to UNLOCK this account?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, please UNLOCK this account!',
                cancelButtonText: 'Cancel',
            })
        }
    };

    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'center',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
                position: 'center',
                timer: 1500
            })
        }
    }

    static IziToast = class {
        static showSuccessAlert(t) {
            iziToast.success({
                title: 'OK',
                position: 'topRight',
                timeout: 2500,
                message: t
            });
        }
    }

    static IziToast = class  {
        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                message: m,
            });
        }
    }

    static formatNumber() {
        $(".num-space").number(true, 0, ',', ' ');
        $(".num-point").number(true, 0, ',', '.');
        $(".num-comma").number(true, 0, ',', ',');
    }

    static formatNumberSpace(x) {
        if (x == null) {
            return x;
        }
        return x.toString().replace(/ /g, "").replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    }
}



class Product {
    constructor(id, productName, category, urlImage, price, quantity, year, capacity, origin,  deleted,status) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.urlImage = urlImage;
        this.price = price;
        this.quantity = quantity;
        this.year = year;
        this.capacity = capacity;
        this.origin = origin;
        this.status = status;
        this.deleted = deleted;
    }
}

class User {
    constructor(id, fullName, username, password, gender, urlImage, phone, email, address, role, statusAccount) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.urlImage = urlImage;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.statusAccount = statusAccount;
    }
}

class Category {
    constructor(id, category) {
        this.id = id;
        this.category = category;
    }
}

class Capacity {
    constructor(id, capacity) {
        this.id = id;
        this.category= capacity;
    }
}

class Status {
    constructor(id, status) {
        this.id = id;
        this.status = status;
    }
}
class StatusAccount {
    constructor(id, statusAccount) {
        this.id = id;
        this.statusAccount = statusAccount;
    }
}

class Role {
    constructor(id, code) {
        this.id = id;
        this.role = code;
    }
}

class Gender {
    constructor(id, gender) {
        this.id = id;
        this.gender = gender;
    }
}