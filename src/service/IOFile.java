package service;

import booking.Menu;
import login.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public void WriteFileAccount(List<Account> accounts) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\modul_2\\modul2_caseStudy\\src\\Account.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(accounts);
        objectOutputStream.close();
        fileOutputStream.close();
    }



    public void WriteFileMenu(List<Menu> menus) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\modul_2\\modul2_caseStudy\\src\\Menu.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(menus);
        objectOutputStream.close();
        fileOutputStream.close();
    }


    public List<Account> readFileAccount() throws IOException {
        List<Account> newAccounts = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream("D:\\modul_2\\modul2_caseStudy\\src\\Account.txt");
        if (fileInputStream.available() != 0) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                newAccounts = (ArrayList<Account>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return newAccounts;
    }

    public List<Menu> readFileMenu() throws IOException {
        List<Menu> newMenu = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream("D:\\modul_2\\modul2_caseStudy\\src\\Menu.txt");
        if (fileInputStream.available() != 0) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                newMenu = (ArrayList<Menu>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return newMenu;
    }
}
