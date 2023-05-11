package booking;

import java.io.Serializable;
import java.time.LocalDate;

public class Cart implements Serializable {
    private static final long serialVersionUID = 3L;
    private String name;
    private LocalDate date;
    private boolean isPaid;

    public Cart() {
    }

    public Cart(String name) {
        this.name = name;
        this.date = LocalDate.now();
        this.isPaid = false;

    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return name + ","+ date ;
    }

    public void display() {
        System.out.printf("%-15s%-15s%-18s%s",
                this.name, this.date + "\n");
    }
}