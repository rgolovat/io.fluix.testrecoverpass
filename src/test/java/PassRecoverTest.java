/**
 * Created by Roman on 11/9/15.
 */

import org.junit.Test;
import org.openqa.selenium.By;


import javax.mail.MessagingException;
import java.io.IOException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class PassRecoverTest {

    @Test
    public void asendLinkTest(){

        PassRecover.openMainPage();

        $("#forgot_link").hover().click();
        $(".heading").shouldHave(exactText("Reset Your password"));

        $("#email").setValue(CredentialsForTest.email);

        $("#confirm_btn").click();

        $(".heading").shouldHave(exactText("Further instructions were sent by email"));



    }

    @Test
    public void bgetLinkFromEmailTest() throws IOException, MessagingException {
        open(LinkForRecover.getLink());
        $("#pwd").setValue(CredentialsForTest.password);
        $("#next_btn").click();
        $("#re_pwd").shouldBe(visible).setValue(CredentialsForTest.password);
        $("#confirm_btn").click();
        $(".heading").shouldHave(exactText("Password created successfully"));

    }

    @Test
    public void loginNewPassTest(){
        PassRecover.openMainPage();

        $("#login").setValue(CredentialsForTest.email);
        $("#password").setValue(CredentialsForTest.password);

        $("#submit").click();

        $(byText("Sign Out")).exists();


    }


}
