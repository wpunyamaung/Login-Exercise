package com.sourcey.materiallogindemo;

import android.app.Application;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.sourcey.materiallogindemo.TestUtils.createAccount;
import static com.sourcey.materiallogindemo.TestUtils.login;
import static com.sourcey.materiallogindemo.TestUtils.verifyLoginEmailErrorMessage;
import static com.sourcey.materiallogindemo.TestUtils.verifyLoginPasswordErrorMessage;
import static com.sourcey.materiallogindemo.TestUtils.verifyLoginSuccessfully;
import static com.sourcey.materiallogindemo.UserLogin.setLoginInfo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test_Login_With_Correct_Email_Correct_Password() {
        //Create new account
        createAccount();

        //Try to login with the created account
        UserLogin testCase1 = setLoginInfo("Test@test.com", "123456");
        UserLogin testCase2 = setLoginInfo("test@test.com", "123456");

        List<UserLogin> testCases = new ArrayList<>();
        testCases.add(testCase1);
        testCases.add(testCase2);

        for (UserLogin info:testCases) {
            login(info.getUserName(), info.getPassword());
            verifyLoginSuccessfully();
        }
    }

    @Test
    public void test_ReLogin_With_Correct_Email_Correct_Password() {
        //Create new account
        createAccount();

        //Try to login with the wrong username and  password
        login("Test@test.com", "1234567");
        verifyLoginPasswordErrorMessage("enter a valid email address or password");

        //Try to login again with the created account
        login("Test@test.com", "123456");
        verifyLoginSuccessfully();
    }

    @Test
    public void test_Login_With_Invalid_Email_Valid_Password() {
        UserLogin testCase1 = setLoginInfo("", "123456");
        UserLogin testCase2 = setLoginInfo("Test@", "123456");

        List<UserLogin> testCases = new ArrayList<>();
        testCases.add(testCase1);
        testCases.add(testCase2);

        for (UserLogin info:testCases) {
            login(info.getUserName(), info.getPassword());

            //Verify if the error massage displayed
            verifyLoginEmailErrorMessage("enter a valid email address");
        }

    }

    @Test
    public void test_Login_With_Valid_Email_Invalid_Password() {
        UserLogin testCase1 = setLoginInfo("Test@test.com", "");
        UserLogin testCase2 = setLoginInfo("Test@test.com", "123");
        UserLogin testCase3 = setLoginInfo("Test@test.com", "0123456789a");

        List<UserLogin> testCases = new ArrayList<>();
        testCases.add(testCase1);
        testCases.add(testCase2);
        testCases.add(testCase3);

        for (UserLogin info:testCases) {
            login(info.getUserName(), info.getPassword());

            //Verify if the error massage displayed
            verifyLoginPasswordErrorMessage("between 4 and 10 alphanumeric characters");
        }
    }

    @Test
    public void test_Login_With_Invalid_Email_Invalid_Password() {
        UserLogin testCase1 = setLoginInfo("", "");
        UserLogin testCase2 = setLoginInfo("Test", "123");

        List<UserLogin> testCases = new ArrayList<>();
        testCases.add(testCase1);
        testCases.add(testCase2);

        for (UserLogin info:testCases) {
            login(info.getUserName(), info.getPassword());

            //Verify if the error massage displayed
            verifyLoginEmailErrorMessage("enter a valid email address");
            verifyLoginPasswordErrorMessage("between 4 and 10 alphanumeric characters");
        }
    }

    @Test
    public void test_Login_With_Incorrect_Email_Incorrect_Password() {
        UserLogin testCase1 = setLoginInfo("Test@test.com", "1234567890");
        UserLogin testCase2 = setLoginInfo("Test1@test.com", "1234");
        UserLogin testCase3 = setLoginInfo("Test1@test.com", "@bcd2345");

        List<UserLogin> testCases = new ArrayList<>();
        testCases.add(testCase1);
        testCases.add(testCase2);
        testCases.add(testCase3);

        for (UserLogin info:testCases) {
            login(info.getUserName(), info.getPassword());

            //Verify if the error massage displayed
            verifyLoginPasswordErrorMessage("enter a valid email address or password");
        }
    }

}

class UserLogin
{
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserLogin setLoginInfo(String userName, String password){
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(userName);
        userLogin.setPassword(password);
        return userLogin;
    }
}