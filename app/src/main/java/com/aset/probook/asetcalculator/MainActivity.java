package com.aset.probook.asetcalculator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<MonthPay> monthPays = new ArrayList<>();
    int monthes = 6;
    // просто поменяйте кол-во месяцев тут и все автоматически вычислиться
    int numberOfMonthes = 9;
    ArrayList<TextView> views;
    boolean phoneCheck = false;

 //   LinearLayout mainLayout;

    RadioButton phone;
    RadioButton other;


    LinearLayout lnPhone;
    LinearLayout lnOther;

    EditText edtSalary;
    EditText edtCredit;
    Button btnConfirm;

    ListView listView;
    int a = 519;        // ограничение ( DTI )


    class adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return numberOfMonthes;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.item_monthes,null);

            TextView textView = view.findViewById(R.id.txt1);
            TextView textView2 = view.findViewById(R.id.txt2);
            TextView textView3 = view.findViewById(R.id.txt3);
            TextView textView4 = view.findViewById(R.id.txt4);


            textView.setText(String.valueOf(6 + i * 3));
            textView2.setText(getResources().getString(R.string.month));
            textView3.setText(String.valueOf(monthPays.get(i).getResult()));
            textView4.setText(getResources().getString(R.string.azn));

            return view;
        }
    }
    adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.aset_bar4);
        actionBar.setTitle("");

        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        adapter = new adapter();


        listView = (ListView)findViewById(R.id.listView);

        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        edtSalary = (EditText) findViewById(R.id.salary);
        edtCredit = (EditText) findViewById(R.id.credit);
        phone = (RadioButton) findViewById(R.id.phone);
        //phoneOther = (CheckBox) findViewById(R.id.phoneOther);
        other = (RadioButton) findViewById(R.id.other);
        lnOther = (LinearLayout) findViewById(R.id.lytOther);
        lnPhone = (LinearLayout) findViewById(R.id.lytPhone);
       // mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        btnConfirm.setOnClickListener(this);

    }

    public LinearLayout getLayout() {
        LinearLayout layout;
        layout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.topMargin = 8;
        params.weight = 1;

        layout.setWeightSum(2);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setBackgroundResource(R.drawable.layout_round_background);
        layout.setLayoutParams(params);

        return layout;
    }

    // creating text view programatically
    // give 0 for left and start for result and 0 for right and end for "6 ay"
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public TextView getView(String text, int left, int start, int right, int end, int size){
        final float scale = getResources().getDisplayMetrics().density;
        TextView view = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if ( left != 0 && start != 0) {
            params.leftMargin = left;
            params.setMarginStart(start);
            // equivalance of @android:gravity = "start"
            params.gravity = 8388611|3;
            params.weight = 2;
        }
        if ( right != 0 && end != 0 )
        {
            params.rightMargin = right;
            params.setMarginEnd(end);
            // equivalance of @android:gravity = "end"
            params.gravity = 8388613|5;
            //params.weight = 1;
        }
        if ( right != 0 && end == 0 )
        {
            params.rightMargin = right;
            params.topMargin = 10;
            // params.gravity = 8388613;
        }
        view.setLayoutParams(params);
        view.setTextColor(Color.WHITE);
        view.setTextSize( (float) size);
        view.setText(text);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }

        int salary = 0;
        int credit = 0;

        // Проверяем поля на пустоту
        if (TextUtils.isEmpty(edtSalary.getText().toString())
                || TextUtils.isEmpty(edtCredit.getText().toString())) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.fill_all_cells, Toast.LENGTH_LONG);
            toast.show();
            listView.setVisibility(View.GONE);
            //listView.removeAllViewsInLayout();
            return;
        }

        // читаем EditText и заполняем переменные числами
        salary = Integer.parseInt(edtSalary.getText().toString());
        credit = Integer.parseInt(edtCredit.getText().toString());


        lnPhone.setVisibility(View.VISIBLE);
        //   lnPhone1315.setVisibility(View.GONE);
        lnOther.setVisibility(View.GONE);

        //lnPhone.setVisibility(View.GONE);
        //   lnPhone1315.setVisibility(View.GONE);
        //lnOther.setVisibility(View.VISIBLE);

        if (phone.isChecked()) {

            phoneCheck = true;
            //lnPhoneOther.setVisibility(View.GONE);
            // определяем нажатую кнопку и выполняем соответствующую операцию
        }
        else if (other.isChecked()){

            phoneCheck = false;
        }

        edtCredit.setOnClickListener(this);
        edtSalary.setOnClickListener(this);



        switch (v.getId()) {
            /*case R.id.salary:

                break;
            case R.id.credit:
                listView.setVisibility(View.GONE);
                Log.d("Here","Here");
                break;*/
            case R.id.btn_confirm:
                listView.setVisibility(View.VISIBLE);
                monthPays.clear();
                monthes = 6;
                for ( int i = 0; i < numberOfMonthes; i++ )
                {
                    MonthPay month = new MonthPay(monthes,salary,credit, phoneCheck);
                    monthPays.add(month);
                    monthes += 3;
                    //views.get(i).setText(String.valueOf(month.getResult()));
                }
                listView.setAdapter(adapter);
                break;

/*
                MonthPay first = new MonthPay(6,salary,credit);
                double result = first.getResult();
                txtPhone6.setText(String.valueOf(result));

                MonthPay first2 = new MonthPay(9,salary,credit);
                double result2 = first2.getResult();
                txtPhone9.setText(String.valueOf(result2));

                MonthPay first3 = new MonthPay(12,salary,credit);
                double result3 = first3.getResult();
                txtPhone12.setText(String.valueOf(result3));

                MonthPay first4 = new MonthPay(15,salary,credit);
                double result4 = first4.getResult();
                txtPhone15.setText(String.valueOf(result4));
*/

               /* int e;
                if (salary > a) { //519
                    e = (int) (salary * 0.5) - credit; // e -> DTI
                }
                else {
                    e = (int) (salary * 0.4) - credit;
                }
                if (e > 0) {
                        DecimalFormat df = new DecimalFormat("#######");
                        double phoneA6 = (e * 100) / phoneX6;
                        phoneA6 = Double.valueOf(df.format(phoneA6));
                        double phoneA9 = (e * 100) / phoneX9;
                        phoneA9 = Double.valueOf(df.format(phoneA9));
                        double phoneA12 = (e * 100) / phoneX12;
                        phoneA12 = Double.valueOf(df.format(phoneA12));

                        double phoneB6 = (e * 100) / phoneY6;
                        phoneB6 = Double.valueOf(df.format(phoneB6));
                        double phoneB9 = (e * 100) / phoneY9;
                        phoneB9 = Double.valueOf(df.format(phoneB9));
                        double phoneB12 = (e * 100) / phoneY12;
                        phoneB12 = Double.valueOf(df.format(phoneB12));

                        double phoneA15 = (e * 100) / phoneY15;
                        phoneA15 = Double.valueOf(df.format(phoneA15));

                        double phoneA18 = (e * 100) / phoneY18;
                        phoneA18 = Double.valueOf(df.format(phoneA18));

                        double phoneB15 = (e * 100) / phoneY15;
                        phoneB15 = Double.valueOf(df.format(phoneB15));

                        double phoneB18 = (e * 100) / phoneY18;
                        phoneB18 = Double.valueOf(df.format(phoneB18));

                        // new values

                        double phoneA21 = (e * 100) / phoneY21;
                        phoneA21 = Double.valueOf(df.format(phoneA21));

                        double phoneA24 = (e * 100) / phoneY24;
                        phoneA24 = Double.valueOf(df.format(phoneA24));

                        double phoneB21 = (e * 100) / phoneY21;
                        phoneB21 = Double.valueOf(df.format(phoneB21));

                        double phoneB24 = (e * 100) / phoneY24;
                        phoneB24 = Double.valueOf(df.format(phoneB24));


                        double phoneA27 = (e * 100) / phoneY27;
                        phoneA27 = Double.valueOf(df.format(phoneA27));

                        double phoneA30 = (e * 100) / phoneY30;
                        phoneA30 = Double.valueOf(df.format(phoneA30));

                        double phoneB27 = (e * 100) / phoneY27;
                        phoneB27 = Double.valueOf(df.format(phoneB27));

                        double phoneB30 = (e * 100) / phoneY30;
                        phoneB30 = Double.valueOf(df.format(phoneB30));



                        if (phoneA6 > 200 && phoneA6 <= 499.99 && phoneB6 > 500 && phoneB6 <= 4000) {
                            txtPhone6.setText(String.valueOf(phoneB6));
                        } else if (phoneA6 > 200 && phoneA6 <= 499.99 && phoneB6 < 500) {
                            txtPhone6.setText(String.valueOf(phoneA6));
                        } else if (phoneA6 > 499.99 && phoneB6 > 500 && phoneB6 <= 4000) {
                            txtPhone6.setText(String.valueOf(phoneB6));
                        } else if (phoneA6 < 200) {
                            txtPhone6.setText("0");
                        } else if (phoneB6 > 4000) {
                            txtPhone6.setText("4000");
                        }

                        if (phoneA9 > 200 && phoneA9 <= 499.99 && phoneB9 > 500 && phoneB9 <= 4000) {
                            txtPhone9.setText(String.valueOf(phoneB9));
                        } else if (phoneA9 > 200 && phoneA9 <= 499.99 && phoneB9 < 500) {
                            txtPhone9.setText(String.valueOf(phoneA9));
                        } else if (phoneA9 > 499.99 && phoneB9 > 500 && phoneB9 <= 4000) {
                            txtPhone9.setText(String.valueOf(phoneB9));
                        } else if (phoneA9 < 200) {
                            txtPhone9.setText("0");
                        } else if (phoneB9 > 4000) {
                            txtPhone9.setText("4000");
                        }
                        if (phoneA12 > 200 && phoneA12 <= 499.99 && phoneB12 > 500 && phoneB12 <= 4000) {
                            txtPhone12.setText(String.valueOf(phoneB12));
                        } else if (phoneA12 > 200 && phoneA12 <= 499.99 && phoneB12 < 500) {
                            txtPhone12.setText(String.valueOf(phoneA12));
                        } else if (phoneA12 > 499.99 && phoneB12 > 500 && phoneB12 <= 4000) {
                            txtPhone12.setText(String.valueOf(phoneB12));
                        } else if (phoneA12 < 200) {
                            txtPhone12.setText("0");
                        } else if (phoneB12 > 4000) {
                            txtPhone12.setText("4000");
                        }

                        if (phoneB15 > 500 && phoneB15 <= 4000){
                            txtPhone15.setText(String.valueOf(phoneB15));
                        }else if (phoneA15 < 500) {
                            txtPhone15.setText("0");
                        } else if (phoneB15 > 4000) {
                            txtPhone15.setText("4000");
                        }

                        if (phoneB18 > 500 && phoneB18 <= 4000){
                            txtPhone18.setText(String.valueOf(phoneB18));
                        }else if (phoneA18 < 500) {
                            txtPhone18.setText("0");
                        } else if (phoneB18 > 4000) {
                            txtPhone18.setText("4000");
                        }

                        if (phoneB21 > 500 && phoneB21 <= 4000){
                            txtPhone21.setText(String.valueOf(phoneB21));
                        }else if (phoneA21 < 500) {
                            txtPhone21.setText("0");
                        } else if (phoneB21 > 4000) {
                            txtPhone21.setText("4000");
                        }

                        if (phoneB24 > 500 && phoneB24 <= 4000){
                            txtPhone24.setText(String.valueOf(phoneB24));
                        }else if (phoneA24 < 500) {
                            txtPhone24.setText("0");
                        } else if (phoneB24 > 4000) {
                            txtPhone24.setText("4000");
                        }

                        if (phoneB27 > 500 && phoneB27 <= 4000){
                            txtPhone27.setText(String.valueOf(phoneB27));
                        }else if (phoneA27 < 500) {
                            txtPhone27.setText("0");
                        } else if (phoneB27 > 4000) {
                            txtPhone27.setText("4000");
                        }

                        if (phoneB30 > 500 && phoneB30 <= 4000){
                            txtPhone30.setText(String.valueOf(phoneB30));
                        }else if (phoneA30 < 500) {
                            txtPhone30.setText("0");
                        } else if (phoneB30 > 4000) {
                            txtPhone30.setText("4000");
                        }
                    } else {
                        lnOther.setVisibility(View.GONE);
                        lnPhone.setVisibility(View.GONE);
//                            lnPhoneOther.setVisibility(View.GONE);
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.cant_give_credit_for_this_parametrs, Toast.LENGTH_LONG);
                        toast.show();
                    }
                    //txtdip.setText(String.valueOf(e));
               // }


                break;
            default:
                break;

        }
    } else if (other.isChecked()) {
        //other.setBackgroundColor(Color.rgb(0,136,203));
        lnOther.setVisibility(View.VISIBLE);
        lnPhone.setVisibility(View.GONE);
       // lnPhoneOther.setVisibility(View.GONE);
        // определяем нажатую кнопку и выполняем соответствующую операцию
        switch (v.getId()) {
            case R.id.btn_confirm:
                int e;
                if (salary > a) {
                    e = (int) (salary * 0.5) - credit;
                }
                else {
                    e = (int) (salary * 0.4) - credit;
                }
                    if (e > 0) {

                        DecimalFormat df = new DecimalFormat("#######");
                        double otherA6 = (e * 100) / otherX6;
                        otherA6 = Double.valueOf(df.format(otherA6));
                        double otherA9 = (e * 100) / otherX9;
                        otherA9 = Double.valueOf(df.format(otherA9));
                        double otherA12 = (e * 100) / otherX12;
                        otherA12 = Double.valueOf(df.format(otherA12));
                        double otherB6 = (e * 100) / otherY6;
                        otherB6 = Double.valueOf(df.format(otherB6));
                        double otherB9 = (e * 100) / otherY9;
                        otherB9 = Double.valueOf(df.format(otherB9));
                        double otherB12 = (e * 100) / otherY12;
                        otherB12 = Double.valueOf(df.format(otherB12));

                        double otherB15 = (e * 100) / otherY15;
                        otherB15 = Double.valueOf(df.format(otherB15));
                        double otherB18 = (e * 100) / otherY18;
                        otherB18 = Double.valueOf(df.format(otherB18));
                        double otherB21 = (e * 100) / otherY21;
                        otherB21 = Double.valueOf(df.format(otherB21));
                        double otherB24 = (e * 100) / otherY24;
                        otherB24 = Double.valueOf(df.format(otherB24));
                        double otherB27 = (e * 100) / otherY27;
                        otherB27 = Double.valueOf(df.format(otherB27));
                        double otherB30 = (e * 100) / otherY30;
                        otherB30 = Double.valueOf(df.format(otherB30));

                        if (otherA6 > 200 && otherA6 <= 499.99 && otherB6 > 500 && otherB6 <= 4000) {
                            txtOther6.setText(String.valueOf(otherB6));
                        } else if (otherA6 > 200 && otherA6 <= 499.99 && otherB6 < 500) {
                            txtOther6.setText(String.valueOf(otherA6));
                        } else if (otherA6 > 499.99 && otherB6 > 500 && otherB6 <= 4000) {
                            txtOther6.setText(String.valueOf(otherB6));
                        } else if (otherA6 < 200) {
                            txtOther6.setText("0");
                        } else if (otherB6 > 4000) {
                            txtOther6.setText("4000");
                        }

                        if (otherA9 > 200 && otherA9 <= 499.99 && otherB9 > 500 && otherB9 <= 4000) {
                            txtOther9.setText(String.valueOf(otherB9));
                        } else if (otherA9 > 200 && otherA9 <= 499.99 && otherB9 < 500) {
                            txtOther9.setText(String.valueOf(otherA9));
                        } else if (otherA9 > 499.99 && otherB9 > 500 && otherB9 <= 4000) {
                            txtOther9.setText(String.valueOf(otherB9));
                        } else if (otherA9 < 200) {
                            txtOther9.setText("0");
                        } else if (otherB9 > 4000) {
                            txtOther9.setText("4000");
                        }

                        if (otherA12 > 200 && otherA12 <= 499.99 && otherB12 > 500 && otherB12 <= 4000) {
                            txtOther12.setText(String.valueOf(otherB12));
                        } else if (otherA12 > 200 && otherA12 <= 499.99 && otherB12 < 500) {
                            txtOther12.setText(String.valueOf(otherA12));
                        } else if (otherA12 > 499.99 && otherB12 > 500 && otherB12 <= 4000) {
                            txtOther12.setText(String.valueOf(otherB12));
                        } else if (otherA12 < 200) {
                            txtOther12.setText("0");
                        } else if (otherB12 > 4000) {
                            txtOther12.setText("4000");
                        }

                        if (otherB15 > 500 && otherB15 <= 4000) {
                            txtOther15.setText(String.valueOf(otherB15));
                        } else if (otherB15 < 500) {
                            txtOther15.setText("0");
                        } else if (otherB15 > 4000) {
                            txtOther15.setText("4000");
                        }
                        if (otherB18 > 500 && otherB18 <= 4000) {
                            txtOther18.setText(String.valueOf(otherB18));
                        } else if (otherB18 < 500) {
                            txtOther18.setText("0");
                        } else if (otherB18 > 4000) {
                            txtOther18.setText("4000");
                        }
                        if (otherB21 > 500 && otherB21 <= 4000) {
                            txtOther21.setText(String.valueOf(otherB21));
                        } else if (otherB21 < 500) {
                            txtOther21.setText("0");
                        } else if (otherB21 > 4000) {
                            txtOther21.setText("4000");
                        }
                        if (otherB24 > 500 && otherB24 <= 4000) {
                            txtOther24.setText(String.valueOf(otherB24));
                        } else if (otherB24 < 500) {
                            txtOther24.setText("0");
                        } else if (otherB24 > 4000) {
                            txtOther24.setText("4000");
                        }
                        if (otherB27 > 500 && otherB27 <= 4000) {
                            txtOther27.setText(String.valueOf(otherB27));
                        } else if (otherB27 < 500) {
                            txtOther27.setText("0");
                        } else if (otherB27 > 4000) {
                            txtOther27.setText("4000");
                        }
                        if (otherB30 > 500 && otherB30 <= 4000) {
                            txtOther30.setText(String.valueOf(otherB30));
                        } else if (otherB30 < 500) {
                            txtOther30.setText("0");
                        } else if (otherB30 > 4000) {
                            txtOther30.setText("4000");
                        }
                    } else {
                        lnOther.setVisibility(View.GONE);
                        lnPhone.setVisibility(View.GONE);
//                            lnPhoneOther.setVisibility(View.GONE);

                        Toast toast = Toast.makeText(getApplicationContext(), R.string.cant_give_credit_for_this_parametrs, Toast.LENGTH_LONG);
                        toast.show();
                    }
                    //txtdip.setText(String.valueOf(e));
               // }


                break;
                */


            default:
                break;
        }
       // phoneCheck = true;
        //monthPays.clear();
        // поменять 6 на минимальное кол-во месяца в случае изменений
       // monthes = 6;
    }
}
