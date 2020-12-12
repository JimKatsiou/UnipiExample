package gr.novi.androidunipiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gr.novi.androidunipiexample.viewmodels.MainViewModel;
import gr.novi.androidunipiexample.viewmodels.MainViewModelListener;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText usernanemEditText;
    private EditText passEditText;
    private TextView errorTextView;
    private MainViewModel viewModel;


    //region Lifecycle Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new MainViewModel();
        viewModel.setMainViewModelListener(viewModelListener);
        loginButton = findViewById(R.id.loginButtonXml);
        errorTextView = findViewById(R.id.errorTextViewXml);
        loginButton.setOnClickListener(loginButtonClick);
        usernanemEditText = findViewById(R.id.usernameEditTextXml);
        passEditText = findViewById(R.id.passwordEditTextXml);
    }

    private void startForum() {
        Intent intent = new Intent(this, ForumsActivity.class);
        startActivity(intent);
    }

    //endregion

    //region Listeners

    private View.OnClickListener loginButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            errorTextView.setVisibility(View.GONE);
            String username = usernanemEditText.getText().toString();
            String password = passEditText.getText().toString();
            viewModel.handleCredentials(username, password);
        }
    };

    private MainViewModelListener viewModelListener = new MainViewModelListener() {
        @Override
        public void success() {
            startForum();
        }

        @Override
        public void loading() {

        }

        @Override
        public void error(String description) {
            errorTextView.setText(description);
            errorTextView.setVisibility(View.VISIBLE);
        }
    };
    //endregion
}