package booking;

import service.IOFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenuManage implements MenuInterface<Menu> {
    public static int idUp;
    Scanner scanner = new Scanner(System.in);
    List<Menu> menus;
    IOFile ioFile = new IOFile();

    {
        try {
            menus = ioFile.readFileMenu();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void creatNewMenu() throws IOException {
        System.out.println(" số thứ tự món ăn: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(" tên món ăn: ");
        String foodName = scanner.nextLine();
        System.out.println(" giá của món ăn: ");
        int prince = Integer.parseInt(scanner.nextLine());
        System.out.println(" Đã thêm thành công món ăn vào Menu");
        Menu menu = new Menu(id, foodName, prince);
        menus.add(menu);
        ioFile.WriteFileMenu(menus);
    }


    @Override
    public void updateMenu() throws IOException {
        System.out.println("Nhập ID món ăn muốn sửa:");
        int id = Integer.parseInt(scanner.nextLine());
        boolean found = false;

        for (int i = 0; i < menus.size(); i++) {
            if (menus.get(i).getId() == id) {
                found = true;
                System.out.println("Nhập số thứ tự món ăn:");
                int foodIndex = Integer.parseInt(scanner.nextLine());
                System.out.println("Nhập tên món ăn:");
                String foodName = scanner.nextLine();
                System.out.println("Nhập giá món ăn:");
                int price = Integer.parseInt(scanner.nextLine());

                menus.get(i).setId(foodIndex);
                menus.get(i).setFoodName(foodName);
                menus.get(i).setPrice(price);

                System.out.println("Món ăn đã được cập nhật thành công.");
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy món ăn phù hợp với ID đã nhập.");
        }

        ioFile.WriteFileMenu(menus);
    }
    @Override
    public void deleteMenu() throws IOException {
        System.out.println(" nhập tên món ăn muốn xóa ");
        String foodName = scanner.nextLine();
        for (int i = 0; i < menus.size(); i++) {
            if (Objects.equals(menus.get(i).getFoodName(), foodName)) {
                menus.remove(menus.get(i));
            }
            ioFile.WriteFileMenu(menus);
        }
    }

    public void disPlayAllMenu() {
        System.out.printf("%-25s%-25s%-25s\n",
                "Số Thứ tự:", "Tên Món ăn", "Giá món ăn");
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }


    public Menu getById() {
        int id;
        do {
            try {
                System.out.println("Nhập lựa chọn của bạn: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn không phù hợp, mời bạn chọn lại!");
            }
        } while (true);
        for (Menu product : menus) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

}

