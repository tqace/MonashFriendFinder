package com.example.dingweichao.monashfriendfinder;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView resultTextView = (TextView) findViewById(R.id.tvResult);
        Button map = (Button) findViewById(R.id.button);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, bMap.class);
                startActivity(intent);
            }
            });
        Button weather = (Button) findViewById(R.id.button2);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });
        Button movie = (Button) findViewById(R.id.button3);
        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MovieInfo.class);
                startActivity(intent);
            }
        });
        //Button findAllCoursesBtn = (Button) findViewById(R.id.btnFindAll);
       /* findAllCoursesBtn.setOnClickListener(
                //create an anonymous AsyncTask
                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        return RestClient.findAllCourses();
                    }
                    @Override
                    protected void onPostExecute(String courses) {
                        resultTextView.setText(courses);
                    }
                }.execute();
            }
        });*/
        /*Button deleteStudent = (Button) findViewById(R.id.deleteCourse);
        final EditText studentID= (EditText) findViewById(R.id.editText);
        deleteStudent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //create an anonymous AsyncTask
                new AsyncTask<Integer, Void, String>() {
                    @Override
                    protected String doInBackground(Integer... params) {
                        RestClient.deleteStudent(params[0]);
                        return "deleted";
                    }
                    @Override
                    protected void onPostExecute(String response) {
                        resultTextView.setText("Student was deleted");
                    }
                }.execute(Integer.valueOf(studentID.getText().toString()));
            }
        });
        final EditText courseId = (EditText) findViewById(R.id.etCourseId);
        final EditText courseTitle = (EditText) findViewById(R.id.etCourseTitle);
        Button addCourse=(Button)findViewById(R.id.btnAddCourse);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... params) {
                        Course course=new Course(Integer.valueOf(params[0]),params[1]);
                        RestClient.createCourse(course);
                        return "Course was added";
                    }
                    @Override
                    protected void onPostExecute(String response) {
                        resultTextView.setText(response);
                    }
                }.execute(courseId.getText().toString(),courseTitle.getText().toString());
            }
        });*/

    }

}
