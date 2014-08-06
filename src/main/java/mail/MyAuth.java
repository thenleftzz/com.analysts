package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by Xerxes on 2014/8/6.
 */
public class MyAuth extends Authenticator {
    String userName = null;
    String password = null;

    public MyAuth() {

    }

    public MyAuth(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName,password);
    }
}
