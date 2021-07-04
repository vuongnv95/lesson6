package com.example.demorealm.room;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demorealm.R;


import java.util.List;

public class RoomActivity extends AppCompatActivity {
    private EditText firstNameEdt;
    private EditText lastNameEdt;
    private TextView listUserTv;
    private UserDatabase userDatabase;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setContentView(R.layout.activity_room);
        initDataBase();
        initViews();
        onClickViews();
    }

    private void initDataBase() {
        userDatabase = UserDatabase.getInstance(this);
    }

    private void initViews() {
        firstNameEdt = findViewById(R.id.first_name_edt);
        lastNameEdt = findViewById(R.id.last_name_edt);
        listUserTv = findViewById(R.id.list_user_tv);
    }

    private void onClickViews() {
        findViewById(R.id.add_user_btn).setOnClickListener(this::addUser);
        findViewById(R.id.get_user_btn).setOnClickListener(this::getListUser);
    }

    private void getListUser(View view) {
        if (userDatabase != null && userDatabase.userDAO() != null) {
            userDatabase.userDAO().insertUser(new UserEntity(firstNameEdt.getText().toString(), lastNameEdt.getText().toString(), new UserEntity.Address("Nam Định")));
        }
    }

    private void addUser(View view) {
        if (userDatabase != null && userDatabase.userDAO() != null) {
            List<UserEntity> listUser = userDatabase.userDAO().getALlUser();
            String listUserString = "";
            for (int i = 0; i < listUser.size(); i++) {
                listUserString += listUser.get(i).toString();
            }
            listUserTv.setText(listUserString);
        }
    }


}
