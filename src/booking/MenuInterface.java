package booking;

import java.io.IOException;

public interface MenuInterface<A> {
    void creatNewMenu() throws IOException;
    void updateMenu() throws IOException;
    void deleteMenu() throws IOException;

}
