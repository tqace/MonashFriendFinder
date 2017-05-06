package com.example.dingweichao.monashfriendfinder;

/**
 * Created by Ding Weichao on 2017/5/3.
 */

import android.util.Log;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RestClient {
    private static final String BASE_URI = "http://192.168.191.3:8080/FriendFinder1.2/webresources";
    public static String findAllCourses() {
        final String methodPath = "/friend.profile/";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }
    public static void deleteStudent(int studentId){
        final String methodPath ="/friend.profile/"+ studentId;
        URL url = null;
        HttpURLConnection conn = null;
        String txtResult="";
        // Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the connection method to GET
            conn.setRequestMethod("DELETE");
            Log.i("error",new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
    public static void createCourse(Course course){
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath="/friend.profile/";
        try {
            Gson gson =new Gson();
            String stringCourseJson=gson.toJson(course);
            url = new URL(BASE_URI + methodPath);
//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
//set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringCourseJson.getBytes().length);
//add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
//Send the POST out
            PrintWriter out= new PrintWriter(conn.getOutputStream());
            out.print(stringCourseJson);
            out.close();
            Log.i("error",new Integer(conn.getResponseCode()).toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
}

