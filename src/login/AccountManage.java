package login;

import service.IOFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AccountManage implements AccountInterface<Account> {
    Scanner scanner = new Scanner(System.in);
    List<Account> accounts;
    IOFile ioFile = new IOFile();

    public static Account currentAccount;

    {
        try {
            accounts = ioFile.readFileAccount();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void creatNewAccount() throws IOException {
        String userName;
        while (true) {
            System.out.println("Tên đăng nhập: ");
            userName = scanner.nextLine();
            if (!userName.equals("")) {
                boolean flag = true;
                for (Account account : accounts) {
                    if (userName.equals(account.getUserName()) || userName.equals("admin")) {
                        System.out.println(" Tài khoản đã tồn tại, vui lòng nhập lại ");
                        flag = false;
                    }
                }
                if (flag && !userName.equals("admin")) {
                    break;
                } else if (userName.equals("admin")) {
                    System.out.println(" Tên đăng nhập đã được sử dụng, vui lòng nhập tên đăng nhập khác");
                }
            } else {
                System.out.println(" Hãy nhập lại tài khoản");
            }

        }
        System.out.println("Mật khẩu: ");
        String password;
        do {
            try {
                password = scanner.nextLine();
                String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
                if (password.matches(regex)) {
                    break;
                } else {
                    throw new IndexOutOfBoundsException(" Mật khẩu bao gồm: Tối thiểu tám ký tự," +
                            " ít nhất một chữ cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt." +
                            " Vui lòng nhập lại ");
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        System.out.println(" Họ và tên: ");
        String fullName = scanner.nextLine();
        System.out.println(" Số điện thoại: ");
        String phoneNumber;
        do {
            try {
                phoneNumber = scanner.nextLine();
                String regex = "0\\d{9}";
                if (phoneNumber.matches(regex)) {
                    for (Account account : accounts) {
                        if (phoneNumber.equals(account.getPhoneNumber())) {
                            throw new IllegalAccessException("số điện thoại đã tồn tại, vui lòng nhập lại");
                        }
                    }
                    break;
                } else {
                    throw new IndexOutOfBoundsException(" Sai định dạng số điện thoại, vui lòng nhập lại");
                }
            } catch (IndexOutOfBoundsException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        System.out.println(" Địa chỉ nhận hàng ( Số nhà/Hẻm/Đường, Phường/Xã, Quận/Huyện, Tỉnh/Thành phố : ");
        String address = scanner.nextLine();
        System.out.println(" Tạo tài khoản thành công!!");
        int id = checkDefaultIndex() + 1;
        Account account = new Account(id, userName, password, fullName, phoneNumber, address);
        accounts.add(account);
        ioFile.WriteFileAccount(accounts);
    }

    @Override

    public boolean loginAccount() {
        boolean checkAccount = false;
        int count = 0;
        do {
            System.out.println(" Tài khoản: ");
            String userName = scanner.nextLine();
            System.out.println(" Mật khẩu: ");
            String password = scanner.nextLine();
            for (Account account : accounts) {
                if (userName.equals(account.getUserName()) && password.equals(account.getPassword())) {
                    System.out.println(" xin chào " + account.getFullName() + "!!!");
                    currentAccount = account;
                    checkAccount = true;
                    break;
                }
            }
            if (!checkAccount) {
                count++;
                System.out.println(" Thông tin tài khoản hoặc mật khẩu không chính xác.");
                if (count >= 3) {
                    System.out.println(" Bạn đã nhập sai quá nhiều lần. Hãy thử lại sau.");
                    break;
                }
                System.out.println(" Vui lòng đăng nhập lại.");
            }
        } while (!checkAccount);
        return checkAccount;

    }

    @Override
    public void updateAccount() throws IOException {
        System.out.println("Số điện thoại hiện tại: " + currentAccount.getPhoneNumber());
        System.out.println("Nhập số điện thoại mới muốn thay đổi:");
        String newPhoneNumber = scanner.nextLine();
        for (Account account : accounts) {
            if (newPhoneNumber.equals(account.getPhoneNumber()) && !newPhoneNumber.equals(currentAccount.getPhoneNumber())) {
                System.out.println("Số điện thoại đã tồn tại, vui lòng nhập lại.");
                updateAccount();
                return;
            }
        }
        if (newPhoneNumber.equals("")) {
            newPhoneNumber = currentAccount.getPhoneNumber();
        }

        System.out.println("Địa chỉ hiện tại: " + currentAccount.getAddress());
        System.out.println("Nhập địa chỉ mới muốn thay đổi:");
        String newAddress = scanner.nextLine();
        if (newAddress.equals("")) {
            newAddress = currentAccount.getAddress();
        }

        currentAccount.setPhoneNumber(newPhoneNumber);
        currentAccount.setAddress(newAddress);
        System.out.println("Cập nhật thông tin tài khoản thành công.");
        ioFile.WriteFileAccount(accounts);
    }

    public void updateAccountForAdmin() throws IOException {
        System.out.println("Nhập id khách hàng muốn sửa:");
        int id = Integer.parseInt(scanner.nextLine());
        Account accountToUpdate = null;
        for (Account account : accounts) {
            if (id == account.getId()) {
                accountToUpdate = account;
                break;
            }
        }

        if (accountToUpdate == null) {
            System.out.println("Không tìm thấy tài khoản với id " + id);
            return;
        }
        System.out.println(" Số điện thoại hiện tại của khách hàng " + accountToUpdate.getId() + " là: " + accountToUpdate.getPhoneNumber());
        System.out.println("Nhập số điện thoại mới muốn thay đổi:");
        String newPhoneNumber = scanner.nextLine();
        if (!newPhoneNumber.isEmpty()) {
            boolean phoneNumberExists = false;
            for (Account account : accounts) {
                if (newPhoneNumber.equals(account.getPhoneNumber()) && account != accountToUpdate) {
                    phoneNumberExists = true;
                    break;
                }
            }

            if (phoneNumberExists) {
                System.out.println("Số điện thoại đã tồn tại, vui lòng nhập lại.");
                return;
            }

            accountToUpdate.setPhoneNumber(newPhoneNumber);
        }

        // Get new address
        System.out.println("Địa chỉ hiện tại của khách hàng " + accountToUpdate.getId() + " là: "+accountToUpdate.getAddress());
        System.out.println("Nhập địa chỉ mới muốn thay đổi:");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            accountToUpdate.setAddress(newAddress);
        }
        System.out.println("Cập nhật thông tin tài khoản thành công.");
        ioFile.WriteFileAccount(accounts);
    }




    public void dellAccountId() throws IOException {
        int id = scanner.nextInt();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == id) {
                accounts.remove(accounts.get(i));
            }
            ioFile.WriteFileAccount(accounts);
        }
    }


    public void displayAll() {
        for (Account account : accounts
        ) {
            System.out.println(account);
        }
    }

    private int checkDefaultIndex() {
        if (accounts.isEmpty()) {
            return 0;
        } else {
            return accounts.get(accounts.size() - 1).getId();
        }
    }

    public void searchAccount() {
        String phoneNumber = currentAccount.getPhoneNumber();
        for (int i = 0; i < accounts.size(); i++) {
            if (Objects.equals(accounts.get(i).getPhoneNumber(), phoneNumber)) {
                System.out.println(accounts.get(i));
                break;
            }
        }
    }


    public boolean loginAdmin() {
        boolean checkAccount = false;
        int count = 0;
        do {
            System.out.println(" Tài khoản: ");
            String userName = scanner.nextLine();
            System.out.println(" Mật khẩu: ");
            String password = scanner.nextLine();
            if (userName.equals("admin") && password.equals("Tuanle@123")) {
                System.out.println(" xin chào admin!!!");
                checkAccount =true;
                break;
            }
            if (!checkAccount) {
                count++;
                System.out.println(" Thông tin tài khoản hoặc mật khẩu không chính xác.");
                if (count >= 3) {
                    System.out.println(" Bạn đã nhập sai quá nhiều lần. Hãy thử lại sau.");
                    break;
                }
                System.out.println(" Vui lòng đăng nhập lại.");
            }
        } while (!checkAccount);

        return checkAccount;
    }
}

