package example.com.xueziapp.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.com.xueziapp.R;

/**
 * Created by 49995 on 2017/3/6.
 */

public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText editPhoneNumber;
    private EditText editCode;
    private EditText editPassword;
    private EditText editAgainPassword;
    private Button btnReceiveCode;
    private Button btnRegister;
    private Button btnNumberClear;
    private Button btnCodeClear;
    private Button btnPassClear;
    private Button btnAgainPassClear;
    private TextWatcher phoneNumberWatcher;
    private TextWatcher codeWatcher;
    private TextWatcher passWatcher;
    private TextWatcher passAgainWatcher;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initView();
    }

    private void initView() {
        editPhoneNumber = (EditText) findViewById(R.id.edit_phone_number);
        editCode = (EditText) findViewById(R.id.edit_security_code);
        editPassword = (EditText) findViewById(R.id.edit_password);
        editAgainPassword = (EditText) findViewById(R.id.edit_password_again);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnReceiveCode = (Button) findViewById(R.id.btn_receive_code);
        btnNumberClear = (Button) findViewById(R.id.phonenumber_clear);
        btnCodeClear = (Button) findViewById(R.id.code_clear);
        btnPassClear = (Button) findViewById(R.id.password_clear);
        btnAgainPassClear = (Button) findViewById(R.id.again_password_clear);
        initWatcher();
        editPhoneNumber.addTextChangedListener(phoneNumberWatcher);
        editCode.addTextChangedListener(codeWatcher);
        editPassword.addTextChangedListener(passWatcher);
        editAgainPassword.addTextChangedListener(passAgainWatcher);
        btnAgainPassClear.setOnClickListener(this);
        btnNumberClear.setOnClickListener(this);
        btnPassClear.setOnClickListener(this);
        btnCodeClear.setOnClickListener(this);
        btnReceiveCode.setOnClickListener(this);
    }

    private void initWatcher() {
        phoneNumberWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {//当输入框有内容时，显示删除按钮
                    btnNumberClear.setVisibility(View.VISIBLE);
                    btnReceiveCode.setTextColor(getResources().getColor(R.color.normalTextColor));

                } else {
                    btnNumberClear.setVisibility(View.INVISIBLE);
                    btnReceiveCode.setTextColor(getResources().getColor(R.color.diableTextColor));

                }
            }
        };

        codeWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {//当输入框有内容时，显示删除按钮
                    btnCodeClear.setVisibility(View.VISIBLE);
                } else {
                    btnCodeClear.setVisibility(View.INVISIBLE);
                }

            }
        };

        passWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {//当输入框有内容时，显示删除按钮
                    btnPassClear.setVisibility(View.VISIBLE);
                } else {
                    btnPassClear.setVisibility(View.INVISIBLE);
                }

            }
        };

        passAgainWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {//当输入框有内容时，显示删除按钮
                    btnAgainPassClear.setVisibility(View.VISIBLE);
                } else {
                    btnAgainPassClear.setVisibility(View.INVISIBLE);
                }

            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phonenumber_clear:
                editPhoneNumber.setText("");
                break;
            case R.id.code_clear:
                editCode.setText("");
                break;
            case R.id.password_clear:
                editPassword.setText("");
                break;
            case R.id.again_password_clear:
                editAgainPassword.setText("");
                break;
            case R.id.btn_receive_code:
                break;
        }
    }
}
