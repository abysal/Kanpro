package com.example.collegeapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.collegeapp.Activity.LoginActivity;
import com.example.collegeapp.Activity.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class InstrumentedTest2 {

    @Rule
    public ActivityTestRule<RegisterActivity> testRule2=new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void RegisterTest() throws Exception{
//        onView(withId(R.id.tvDisplay)).check(matches(withText(mText)));
        onView(withId(R.id.et_first_name)).perform(typeText("Pradip")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_last_name)).perform(typeText("Kandel")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_email)).perform(typeText("instrumented@gmail.com")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_batch)).perform(typeText("19")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_section)).perform(typeText("D")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_user_name)).perform(typeText("instrumented")).perform(closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("instrumented")).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_register)).perform(click());
        onView(withId(R.id.btn_login)).check(matches(withText("CONTINUE")));
        //onView(withText("Welcome Manjish")).inRoot(withDecorView(not(is(testRule2.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        //9638512_PradipKandel_CollegeAppForItsActitiesAndNotificationss


    }
}
