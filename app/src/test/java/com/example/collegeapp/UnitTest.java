package com.example.collegeapp;

import com.example.collegeapp.Activity.FeedbackActivity;
import com.example.collegeapp.Adapter.FeedbackAdapter;
import com.example.collegeapp.BusinessLogics.DeleteFeedbackLogic;
import com.example.collegeapp.BusinessLogics.FeedbackLogic;
import com.example.collegeapp.BusinessLogics.GetAssignmentLogic;
import com.example.collegeapp.BusinessLogics.GetCourseLogic;
import com.example.collegeapp.BusinessLogics.LoginLogic;
import com.example.collegeapp.BusinessLogics.RegisterLogic;
import com.example.collegeapp.Model.Assignment;
import com.example.collegeapp.Model.Course;
import com.example.collegeapp.Model.LoginResponse;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnitTest {
    @Test
    public void loginTest(){
        LoginLogic loginLogic=new LoginLogic("user@gmail.com","manjish");
       boolean result=loginLogic.checkUser();
        assertEquals(true,result);

    }

    @Test
    public void loginTestError(){
        LoginLogic loginLogic=new LoginLogic("user1@gmail.com","password");
        boolean result=loginLogic.checkUser();
        assertEquals(true,result);

    }

    @Test
    public void registerTest(){
        RegisterLogic registerLogic=new RegisterLogic("test","test_surname","test6@gmail.com","12",
                "D","Male","","test_user","password");
        boolean result=registerLogic.addUser();
        assertEquals(true,result);

    }

    @Test
    public void feedbackPostTest(){
        FeedbackLogic feedbackLogic=new FeedbackLogic(FeedbackActivity.u_id,"test feedback register 2");
        boolean result=feedbackLogic.addFeedback();
        assertEquals(true,result);

    }

    /*@Test
    public void deleteFeedbackTest(){
        DeleteFeedbackLogic deleteFeedbackLogic=new DeleteFeedbackLogic(FeedbackAdapter.feedback_id);
        boolean result=deleteFeedbackLogic.deleteFeedback();
        assertEquals(true,result);

    }*/

    @Test
    public void getCourseTest(){
        GetCourseLogic getCourseLogic=new GetCourseLogic();
        List<Course> result=getCourseLogic.getAllCourses();
        String cname=result.get(0).getCourse_name();
        assertEquals("BBA",cname);
    }

    @Test
    public void getAssignmentTest(){
        GetAssignmentLogic getAssignmentLogic=new GetAssignmentLogic();
        List<Assignment> result=getAssignmentLogic.getAllAssignment();
        String cname=result.get(0).getModule_name();
        assertEquals("Individual Project Computing",cname);
    }
}
