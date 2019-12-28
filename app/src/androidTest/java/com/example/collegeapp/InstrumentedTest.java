package com.example.collegeapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.collegeapp.Activity.FeedbackActivity;
import com.example.collegeapp.Activity.LoginActivity;
import com.example.collegeapp.Activity.RegisterActivity;
import com.example.collegeapp.Activity.UpdateProfile;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class InstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> testRule1=new ActivityTestRule<>(LoginActivity.class);
    private String login_username="user@gmail.com";
    private String login_password="manjish";

    @Test
    public void LoginTest() throws Exception{
//        onView(withId(R.id.tvDisplay)).check(matches(withText(mText)));

        onView(withId(R.id.et_user_name)).perform(typeText(login_username)).perform(closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText(login_password)).perform(closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.user_email)).check(matches(withText("user@gmail.com")));
        //onView(withText("Welcome Manjish")).inRoot(withDecorView(not(is(testRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        //9638512_PradipKandel_CollegeAppForItsActitiesAndNotificationss
    }

    @Rule
    public ActivityTestRule<RegisterActivity> testRule2=new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void RegisterTest() throws Exception{
        onView(withId(R.id.et_first_name)).perform(typeText("Pradip")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_last_name)).perform(typeText("Kandel")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_email)).perform(typeText("instrumented@gmail.com")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_batch)).perform(typeText("19")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_section)).perform(typeText("D")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_user_name)).perform(typeText("instrumented")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("instrumented")).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_register)).perform(click());
        onView(withId(R.id.btn_login)).check(matches(withText("CONTINUE")));
    }
    @Rule
    public ActivityTestRule<FeedbackActivity> testRule3=new ActivityTestRule<>(FeedbackActivity.class);
    private String feedback="today feedback";
    @Test
    public void FeedbackTest() throws Exception{
        onView(withId(R.id.et_feedback)).perform(typeText(feedback)).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_submit_feedback)).perform(click());
        onView(withId(R.id.user_email)).check(matches(withText("user@gmail.com")));
    }

    @Rule
    public ActivityTestRule<UpdateProfile> testRule4=new ActivityTestRule<>(UpdateProfile.class);

    @Test
    public void UpdateUserTest() throws Exception{
        String  NewName="Pradip";
        String NewSurname="Kandel";
        String NewPassword="user";
        onView(withId(R.id.et_first_name)).perform(replaceText(NewName)).perform(closeSoftKeyboard());
        onView(withId(R.id.et_last_name)).perform(replaceText(NewName)).perform(closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText(NewPassword)).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_update)).perform(click());
        onView(withId(R.id.user_full_name)).check(matches(withText(NewName+" "+NewSurname)));
    }
}
