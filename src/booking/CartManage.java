package booking;


import java.io.*;
import java.util.*;

import static login.AccountManage.currentAccount;

public class CartManage {
    private final Scanner scanner = new Scanner(System.in);
    private final String PATH_FILE = "D:\\modul_2\\modul2_caseStudy\\src\\Cart.txt";
    private List<Cart> carts = new ArrayList<>();
    private ArrayList<CartDetail> cartDetails;
    private MenuManage menuManage;

    public CartManage(MenuManage menuManage) {
        this.menuManage = menuManage;
        cartDetails = readBinary(PATH_FILE);
        checkDefaultIndex();
    }

    private void checkDefaultIndex() {
        if (cartDetails.isEmpty()) {
            CartDetail.idUpCart = 0;
        } else {
            CartDetail.idUpCart = cartDetails.get(cartDetails.size() - 1).getId();
        }
    }

    public void cart() {
        String phoneNumber = currentAccount.getPhoneNumber();
        Cart cart = null;
        for (Cart i : carts) {
            if (i.getName().equals(phoneNumber) && !i.isPaid()) {
                cart = i;
                break;
            }
        }
        if (cart == null) {
            cart = new Cart(phoneNumber);
            carts.add(cart);
        }
        menuManage.disPlayAllMenu();
        Menu selectedMenu = menuManage.getById();
        if (selectedMenu != null) {
            System.out.println("Nhập số lượng: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            addCart(selectedMenu, quantity, cart);
        } else {
            System.out.println("Lựa chọn món ăn không hợp lệ.");
        }
    }

    public void addCart(Menu menu, int quantity, Cart cart) {
        CartDetail cartDetail = new CartDetail(cart, menu, quantity);
        cartDetails.add(cartDetail);
        writeBinary(PATH_FILE, cartDetails);
    }

    public CartDetail updateCart() {
        displayCart();
        CartDetail cartDetail = getById();
        if (cartDetail != null) {
            System.out.println("Đổi món ăn:");
            menuManage.disPlayAllMenu();
            Menu selectedMenu = menuManage.getById();
            if (selectedMenu != null) {
                cartDetail.setMenu(selectedMenu);
            } else {
                System.out.println("Món ăn không tồn tại.");
            }
            System.out.println("Đổi số lượng món ăn:");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity != 0) {
                cartDetail.setQuantity(quantity);
            }
            // Thêm đoạn code sau để cập nhật lại giỏ hàng
            int cartIndex = cartDetails.indexOf(cartDetail);
            if (cartIndex != -1) {
                cartDetails.set(cartIndex, cartDetail);
            }
        }
        writeBinary(PATH_FILE, cartDetails);
        return cartDetail;
    }


    public void deleteCart() {
        String phoneNumber = currentAccount.getPhoneNumber();
        CartDetail cartToRemove = null;
        for (CartDetail cartDetail : cartDetails) {
            if (cartDetail.getCart().getName().equals(phoneNumber)) {
                cartToRemove = cartDetail;
                break;
            }
        }
        if (cartToRemove != null) {
            cartDetails.clear();
            System.out.println("Đã xóa giỏ hàng của tài khoản đã đăng nhập.");
        } else {
            System.out.println("Không tìm thấy giỏ hàng của tài khoản đã đăng nhập.");
        }
        writeBinary(PATH_FILE, cartDetails);
    }

    public CartDetail getById() {
        int idCart;
        do {
            try {
                System.out.println("Nhập ID của món ăn:");
                idCart = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Đầu vào không hợp lệ. Vui lòng thử lại!");
            }
        } while (true);

        for (CartDetail cartDetail : cartDetails) {
            if (idCart == cartDetail.getId()) {
                return cartDetail;
            }
        }
        return null;
    }

    public void displayCart() {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%n",
                "Id", "Số điện thoại đặt hàng", "Date-buy", "Số thứ tự món", "Tên món ăn", "Giá món ăn", "Số lượng đặt");
        for (CartDetail cartDetail : cartDetails) {
            if (cartDetail.getCart().getName().equals(currentAccount.getPhoneNumber())) {
                cartDetail.display();
            }
        }
        calculateTotalAmount();
    }

    public void calculateTotalAmount() {
        double sum = 0;
        String phoneNumber = currentAccount != null ? currentAccount.getPhoneNumber() : null;
        for (CartDetail cartDetail : cartDetails) {
            if (cartDetail.getCart().getName().equals(phoneNumber)) {
                double price = cartDetail.getMenu().getPrice();
                int quantity = cartDetail.getQuantity();
                sum += price * quantity;
            }
        }
        System.out.println("Tổng số tiền phải trả: " + sum + " VND");
    }


    public static void writeBinary(String path, ArrayList<CartDetail> cartDetails) {
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(cartDetails);
            output.close();
            file.close();
        } catch (IOException e) {
            System.err.println("Error!!!");
        }
    }
    public void payForCart() {
        String phoneNumber = currentAccount.getPhoneNumber();
        boolean cartFound = false;
        for (Cart cart : carts) {
            if (cart.getName().equals(phoneNumber) && !cart.isPaid()) {
                cartFound = true;
                System.out.println("Thông tin đơn hàng:");
                displayCart();
                System.out.println("Bạn có chắc chắn muốn thanh toán đơn hàng này? (có/không)");
                String confirm = scanner.nextLine().toLowerCase();
                if (confirm.equalsIgnoreCase("có")) {
                    System.out.println("Đơn hàng đã được thanh toán thành công.");
                    cart.setPaid(true);
                    writeBinary(PATH_FILE, cartDetails);

                    System.out.println("Bạn muốn giao hàng tới địa chỉ: " + currentAccount.getAddress() + "(có/không)");
                    String deliveryOption = scanner.nextLine().toLowerCase();
                    if (deliveryOption.equalsIgnoreCase("có")) {
                        // Lập lịch hiển thị thông báo giao hàng thành công sau 30 giây
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                System.out.println("Đã giao hàng thành công, chúc quý khách ngon miệng!!!mlem mlem");
                                cartDetails.removeIf(cd -> cd.getCart().equals(cart));
                                writeBinary(PATH_FILE, cartDetails);
                            }
                        }, 5000);
                    } else {
                        System.out.println("Đã hủy thanh toán đơn hàng.");
                        cart.setPaid(false);
                    }
                    break;
                }
            }
        }

        if (!cartFound) {
            System.out.println("Không tìm thấy đơn hàng chưa thanh toán của bạn.");
        }

        writeBinary(PATH_FILE, cartDetails);
    }

    public ArrayList<CartDetail> readBinary(String path) {
        File file = new File(path);
        ArrayList<CartDetail> cartDetails = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            cartDetails = (ArrayList<CartDetail>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cartDetails;
    }
}

