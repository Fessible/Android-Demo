package example.com.meituanlogin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 49995 on 2017/3/6.
 */

//账号登录界面的Fragment
public class AccountLoginFragment extends Fragment implements View.OnClickListener {
    private EditText editAccount;//账号
    private EditText editPassword;//密码编辑框
    private Button btnLogin;//登录按钮
    private Button btnAccountClear;//账号清空按钮
    private Button btnPasswordClear;//密码清空按钮
    private Button btnEye;//密码是否可视化
    private TextView textForget;//忘记密码
    public final  static  int LOGIN_ENABLE = 0;
    public final static int LOGIN_UNABLE = 1;
    public final static int PASS_ERR = 3;
    public final  static  int ACCOUNT_ERR = 4;
    private TextWatcher accountWatcher;//账号输入框监听
    private TextWatcher passwordWatcher;//密码输入框的监听

    final Handler uiManagerHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOGIN_ENABLE:
                    btnLogin.setBackgroundResource(R.drawable.login_pressed);
                    btnLogin.setClickable(true);//登录按钮可点击
                    break;
                case LOGIN_UNABLE:
                    btnLogin.setClickable(false);//登录按钮不可点击
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_login_fragment, null);
        Init(view);//初始化控件
        return view;
    }

    //初始化控件
    private void Init(View view) {
        editAccount = (EditText) view.findViewById(R.id.edit_account);
        editPassword = (EditText) view.findViewById(R.id.edit_password);
        btnAccountClear = (Button) view.findViewById(R.id.account_clear);
        btnPasswordClear = (Button) view.findViewById(R.id.password_clear);
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        btnEye = (Button) view.findViewById(R.id.btn_eye);
        textForget = (TextView) view.findViewById(R.id.forget_password);
        btnAccountClear.setOnClickListener(this);
        btnPasswordClear.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnEye.setOnClickListener(this);
        btnLogin.setClickable(false);
        textForget.setOnClickListener(this);
        initWatcher();//用来监听editText的内容并作出相应的动作
        editPassword.addTextChangedListener(passwordWatcher);
        editAccount.addTextChangedListener(accountWatcher);

    }

    private void initWatcher() {
        accountWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {//设置删除键
                editPassword.setText("");
                if (s.toString().length() > 0) {//当输入框有内容时，显示删除按钮
                    btnAccountClear.setVisibility(View.VISIBLE);
                } else {
                    btnAccountClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        passwordWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {//设置密码可见与否
                if (s.toString().length() > 0) {
                    btnPasswordClear.setVisibility(View.VISIBLE);
                } else {
                    btnPasswordClear.setVisibility(View.INVISIBLE);
                }
            }
        };


    }



    //按钮的点击事件监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_clear:
                editAccount.setText("");
                editPassword.setText("");
                break;
            case R.id.password_clear:
                editPassword.setText("");
                break;
            case R.id.btn_login:
                Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_eye:
                if (editPassword.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    btnEye.setBackgroundResource(R.drawable.password_show_eye);
                    editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);

                } else {
                    btnEye.setBackgroundResource(R.drawable.password_hide_eye);
                    editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                editPassword.setSelection(editPassword.getText().toString().length());
                break;
            case R.id.forget_password:
                break;
            default:
                break;
        }
    }
}
