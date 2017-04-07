package example.com.xueziapp.login;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import example.com.xueziapp.R;

/**
 * Created by 49995 on 2017/3/6.
 */

//手机号码登录界面的Fragment
public class PhoneNumberLoginFragment extends Fragment implements View.OnClickListener {
    private EditText editPhoneNumber;//手机号编辑框
    private EditText editCode;//验证码编辑框
    private Button btnReceiveCode;//获取验证码
    private Button btnNumberClear;//手机号清空
    private Button btnLogin;//登录按钮
    private Button btnCodeClear;//验证码清空
    private TextWatcher phoneNumberWatcher;
    private TextWatcher codeWatcher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phonenumber_login,null);
        init(view);//初始化控件
        return view;
    }

    //初始化控件
    private void init(View view) {
        editPhoneNumber = (EditText) view.findViewById(R.id.edit_phone_number);
        editCode = (EditText) view.findViewById(R.id.edit_security_code);
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        btnLogin.setClickable(false);
        btnNumberClear = (Button) view.findViewById(R.id.phonenumber_clear);
        btnReceiveCode = (Button) view.findViewById(R.id.btn_receive_code);
        btnCodeClear = (Button) view.findViewById(R.id.code_clear);
        btnLogin.setOnClickListener(this);
        btnNumberClear.setOnClickListener(this);
        btnNumberClear.setOnClickListener(this);
        btnReceiveCode.setOnClickListener(this);
        btnCodeClear.setOnClickListener(this);
        initWatcher();
        editPhoneNumber.addTextChangedListener(phoneNumberWatcher);
        editCode.addTextChangedListener(codeWatcher);
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
                if (s.toString().length() > 0) {
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
                if (s.toString().length() > 0) {
                    btnCodeClear.setVisibility(View.VISIBLE);

                } else {
                    btnCodeClear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }


    //点击监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                break;
            case R.id.btn_receive_code:
                break;
            case R.id.phonenumber_clear:
                editPhoneNumber.setText("");
                break;
            case R.id.code_clear:
                editCode.setText("");
                break;
        }
    }
}
