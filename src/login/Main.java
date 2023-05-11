package login;

import booking.CartManage;
import booking.MenuManage;

import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        AccountManage accountManage = new AccountManage();
        MenuManage menuManage = new MenuManage();
        CartManage cartManage = new CartManage(menuManage);
        Menu(accountManage, scanner, menuManage, cartManage);
    }

    public static void Menu(AccountManage accountManage, Scanner scanner, MenuManage menuManage, CartManage cartManage) {
        do {
            System.out.println("Mời quý khách lựa chọn:");
            System.out.println("1. Ăn tại nhà hàng");
            System.out.println("2. Đặt mang về ");
            System.out.println("0. Thoát");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 || choice > 2) {
                    throw new IndexOutOfBoundsException(" Lựa chọn không lợp lệ, mời chọn lại menu");
                }
                switch (choice) {
                    case 1:
                        System.out.println("---- Nhà hàng tạm thời đóng cửa, chỉ đặt mang về----");
                        break;
                    case 2:
                        MenuAccount(accountManage, scanner, menuManage, cartManage);
                        break;
                    case 0:
                        System.exit(0);
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(" menu phải nhập số nguyên, mời chọn lại menu");
            }
        } while (true);

    }
    public static void MenuAccount(AccountManage accountManage, Scanner scanner, MenuManage menuManage, CartManage cartManage) {
        do {
            System.out.println("---Menu---");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("3. Đăng nhập bằng admin");
            System.out.println("0. Thoát");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 || choice > 4) {
                    throw new IndexOutOfBoundsException(" Lựa chọn không lợp lệ, mời chọn lại menu");
                }
                switch (choice) {
                    case 1:
                        boolean isLoggedIn = accountManage.loginAccount();
                        if (isLoggedIn) {
                            UserMenu(accountManage,scanner,menuManage, cartManage);
                        }
                        break;
                    case 2:
                        accountManage.creatNewAccount();
                        break;
                    case 3:
                        boolean checkLog = accountManage.loginAdmin();
                        if (checkLog) {
                            AdminMenu(menuManage, scanner, accountManage, cartManage);
                        }
                        break;
                    case 4:
                        accountManage.dellAccountId();
                        break;
                    case 0:
                        System.exit(0);
                        break;

                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(" menu phải nhập số nguyên, mời chọn lại menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }
    public static void UserMenu(AccountManage accountManage, Scanner scanner, MenuManage menuManage, CartManage cartManage) {
        do {
            System.out.println("-----Menu-----");
            System.out.println("1. Đặt đồ ăn");
            System.out.println("2. Kiểm tra đơn hàng");
            System.out.println("3. Tiến hành thanh toán");
            System.out.println("4. Thay đổi đơn hàng");
            System.out.println("5. Hủy đơn hàng");
            System.out.println("6. Thay đổi thông tin cá nhân");
            System.out.println("7. Xem thông tin cá nhân");
            System.out.println("0.git Đặt xong ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 || choice > 7) {
                    throw new IndexOutOfBoundsException(" Lựa chọn không lợp lệ, mời chọn lại menu");
                }
                switch (choice) {
                    case 1:
                        cartManage.cart();
                        break;
                    case 2:
                        cartManage.displayCart();
                        break;
                    case 3:
                        cartManage.payForCart();
                        break;
                    case 4:
                        cartManage.updateCart();

                        break;
                    case 5:
                        cartManage.deleteCart();
                        break;
                    case 6:
                        accountManage.updateAccount();
                        break;
                    case 7:
                        accountManage.searchAccount();
                        break;
                    case 0:
                        MenuAccount(accountManage, scanner, menuManage, cartManage);
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(" menu phải nhập số nguyên, mời chọn lại menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }
    public static void AdminMenu(MenuManage menuManage, Scanner scanner, AccountManage accountManage, CartManage cartManage) {
        do {
            System.out.println("---Menu---");
            System.out.println("1. Thêm món ăn ");
            System.out.println("2. Sửa món ăn ");
            System.out.println("3. Xóa món ăn ");
            System.out.println("4. Hiển thị tất cả món ăn ");
            System.out.println("5. Kiểm tra thông tin các khách hàng ");
            System.out.println("6. Thay đổi thông tin khách hàng");
            System.out.println("0. Thoát ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 || choice > 6) {
                    throw new IndexOutOfBoundsException(" Lựa chọn không lợp lệ, mời chọn lại menu");
                }
                switch (choice) {
                    case 1:
                        menuManage.disPlayAllMenu();
                        menuManage.creatNewMenu();
                        break;
                    case 2:
                        menuManage.updateMenu();
                        break;
                    case 3:
                        menuManage.deleteMenu();
                        break;
                    case 4:
                        menuManage.disPlayAllMenu();
                        break;
                    case 5:
                        accountManage.displayAll();
                        break;
                    case 6:
                        accountManage.displayAll();
                        accountManage.updateAccountForAdmin();
                        break;
                    case 0:
                        MenuAccount(accountManage, scanner, menuManage, cartManage);
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(" menu phải nhập số nguyên, mời chọn lại menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }
}


