package com.sourcey.materiallogindemo;

        import static android.support.test.espresso.Espresso.closeSoftKeyboard;
        import static android.support.test.espresso.Espresso.onView;
        import static android.support.test.espresso.action.ViewActions.clearText;
        import static android.support.test.espresso.action.ViewActions.click;
        import static android.support.test.espresso.action.ViewActions.typeText;
        import static android.support.test.espresso.assertion.ViewAssertions.matches;
        import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
        import static android.support.test.espresso.matcher.ViewMatchers.withId;
        import static android.support.test.espresso.matcher.ViewMatchers.withText;
        import static org.hamcrest.Matchers.notNullValue;

public class TestUtils {

    public static void createAccount(){

        //Start fill in the information for sign-up
        closeSoftKeyboard();
        onView(withId(R.id.link_signup)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.input_name)).perform(click(), typeText("Test"));
        closeSoftKeyboard();
        onView(withId(R.id.input_address)).perform(click(), typeText("Test"));
        closeSoftKeyboard();
        onView(withId(R.id.input_email)).perform(click(), typeText("Test@test.com"));
        closeSoftKeyboard();
        onView(withId(R.id.input_mobile)).perform(click(), typeText("0812345678"));
        closeSoftKeyboard();
        onView(withId(R.id.input_password)).perform(click(), typeText("123456"));
        closeSoftKeyboard();
        onView(withId(R.id.input_reEnterPassword)).perform(click(), typeText("123456"));
        closeSoftKeyboard();
        onView(withId(R.id.btn_signup)).perform(click());
        //Verify if create account successfully
        verifyLoginSuccessfully();
    }

    public static void login(String email, String password){
        onView(withId(R.id.input_email)).perform(click(), clearText());
        onView(withId(R.id.input_email)).perform(click(), typeText(email));
        onView(withId(R.id.input_password)).perform(click(), clearText());
        onView(withId(R.id.input_password)).perform(click(), typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
    }

    public static void verifyLoginEmailErrorMessage(String errorMessage){
        onView(withId(R.id.input_email)).perform(click());
        onView(withId(R.id.input_email)).check(matches(hasErrorText(errorMessage)));
    }

    public static void verifyLoginPasswordErrorMessage(String errorMessage){
        onView(withId(R.id.input_password)).perform(click());
        onView(withId(R.id.input_password)).check(matches(hasErrorText(errorMessage)));
    }

    public static void verifyLoginSuccessfully(){
        onView(withText("Hello world!")).check(matches(notNullValue()));
        onView(withId(R.id.btn_logout)).perform(click());
    }


}
