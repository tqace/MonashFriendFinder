package com.example.dingweichao.monashfriendfinder;

/**
 * Created by tangqu on 17/5/6.
 */


import android.support.v7.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.view.inputmethod.InputMethodManager;

import android.widget.ArrayAdapter;
import android.widget.*;

import android.widget.DatePicker;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Me extends AppCompatActivity {


    @BindView(R2.id.spinner1_me)
    BetterSpinner spinner1;

    @BindView(R2.id.spinner2_me)
    BetterSpinner spinner2;

    @BindView(R2.id.spinner3_me)
    BetterSpinner spinner3;

    @BindView(R2.id.spinner4_me)
    BetterSpinner spinner4;

    @BindView(R2.id.spinner5_me)
    BetterSpinner spinner5;

    @BindView(R2.id.spinner6_me)
    BetterSpinner spinner6;

    @BindView(R2.id.edit_text_dob_me)
    EditText edit_text_dob_me;


    private Calendar calendar; // 通过Calendar获取系统时间
    private int mYear;
    private int mMonth;
    private int mDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me);
        ButterKnife.bind(this);
        calendar = Calendar.getInstance();


        edit_text_dob_me.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Me.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                // TODO Auto-generated method stub
                                mYear = year;
                                mMonth = month;
                                mDay = day;
                                // 更新EditText控件日期 小于10加0
                                edit_text_dob_me.setText(new StringBuilder()
                                        .append(mYear)
                                        .append("-")
                                        .append((mMonth + 1) < 10 ? "0"
                                                + (mMonth + 1) : (mMonth + 1))
                                        .append("-")
                                        .append((mDay < 10) ? "0" + mDay : mDay));
                            }
                        }, calendar.get(Calendar.YEAR), calendar
                        .get(Calendar.MONTH), calendar
                        .get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        String[] list1 = getResources().getStringArray(R.array.gender);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list1);

        String[] list2 = getResources().getStringArray(R.array.course);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list2);

        String[] list3 = getResources().getStringArray(R.array.study_mode);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list3);

        String[] list4 = getResources().getStringArray(R.array.nationality);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list4);

        String[] list5 = getResources().getStringArray(R.array.native_laguage);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list5);

        String[] list6 = getResources().getStringArray(R.array.favorite_unit);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list6);


        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner4.setAdapter(adapter4);
        spinner5.setAdapter(adapter5);
        spinner6.setAdapter(adapter6);


    }

}