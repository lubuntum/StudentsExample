package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Все элементы интерфейса задействованные в приложении
    Button backgroundColorBtn;
    Button selfColorBtn;
    FloatingActionButton settingsColor;
    Button userColorBtn;
    public EditText redColor;
    EditText greenColor;
    EditText blueColor;

    boolean isFirstClick = true;
    //Главный метод где все инициализируется и загружается
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Кнопки
        backgroundColorBtn = findViewById(R.id.change_back_color);
        selfColorBtn = findViewById(R.id.change_self);
        settingsColor = findViewById(R.id.settings);
        //Кнопка и поля для смены цвета пользователем
        userColorBtn = findViewById(R.id.user_color);
        redColor = findViewById(R.id.red);
        greenColor = findViewById(R.id.green);
        blueColor = findViewById(R.id.blue);
        //Методы которые инициализируют функционал приложения
        changeBackColorBtnInit();
        selfColorBtnInit();
        settingsBtnInit();
        userChangeColorBtnInit();
    }
    private void userChangeColorBtnInit(){

        userColorBtn.setOnClickListener((btn)->{
            try {
                //Берем значения введенные пользователем в текстовых полях, а затем меням цвет
                int red = Integer.parseInt(redColor.getText().toString());
                int green = Integer.parseInt(greenColor.getText().toString());
                int blue = Integer.parseInt(blueColor.getText().toString());

                ColorStateList colorStateList = ColorStateList.valueOf(Color.rgb(red, green, blue));
                selfColorBtn.setBackgroundTintList(colorStateList);
                backgroundColorBtn.setBackgroundTintList(colorStateList);
                settingsColor.setBackgroundTintList(colorStateList);
                userColorBtn.setBackgroundTintList(colorStateList);
            }
            catch (Exception e){
                Toast.makeText(this, "Пожалуйста проверьте формат введенных данных от 0 до 255", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Метод для рандомной смены цвета заднего фона
    private void changeBackColorBtnInit(){
        backgroundColorBtn.setOnClickListener((btn)->{
            View root = getWindow().getDecorView().getRootView();
            ColorStateList colorStateList = ColorStateList.valueOf(generateColor());
            root.setBackgroundTintList(colorStateList);
            if(isFirstClick) {
                Toast.makeText(this, "Как все программисты мы не успели сделать настройки)", Toast.LENGTH_LONG).show();
                isFirstClick = !isFirstClick;
            }
        });
    }
    //Метод для смены цвета кнопки
    private void selfColorBtnInit(){
        selfColorBtn.setOnClickListener((btn)->{
            btn.setBackgroundTintList(ColorStateList.valueOf(generateColor()));
        });
    }
    //Вертим настройки!
    private void settingsBtnInit(){
        settingsColor.setOnClickListener((fBtn)->{
            RotateAnimation rotateAnimation =
                    new RotateAnimation(0, 360,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(1000);
            fBtn.startAnimation(rotateAnimation);

        });
    }
    //Метод для генерации рандомного RGB цвета, используется для применения рандомного цвета
    public int generateColor(){
        Random random = new Random();
        int red = random.nextInt(255);
        int green =  random.nextInt(255);
        int blue = random.nextInt(255);
        return Color.rgb(red, green, blue);
    }
}