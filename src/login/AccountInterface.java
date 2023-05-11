package login;

import java.io.IOException;

public interface AccountInterface<A> {
    void creatNewAccount() throws IOException;
    boolean loginAccount();
    void updateAccount() throws IOException;
}
