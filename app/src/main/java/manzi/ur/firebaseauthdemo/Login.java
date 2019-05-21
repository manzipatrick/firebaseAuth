package manzi.ur.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText etemail, etpassword;
    Button btnsignin;
    TextView signuplink;
    ProgressDialog progressDialog;
    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myAuth= FirebaseAuth.getInstance();

        progressDialog= new ProgressDialog(this);

        etemail = (EditText)findViewById(R.id.etemail);
        etpassword = (EditText)findViewById(R.id.etpassword);
        signuplink = (TextView) findViewById(R.id.signuplink);
        btnsignin = (Button) findViewById(R.id.btnsignin);

        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosignupform= new Intent(Login.this, MainActivity.class);
                startActivity(gotosignupform);
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
            }
        });

    }

    public void userlogin() {
        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();


        if (email.isEmpty() ){
            // Email is impty
            Toast.makeText(Login.this, "Please Enter Email",Toast.LENGTH_LONG).show();
        }

        else if (password.isEmpty()){
            // Password is impty
            Toast.makeText(Login.this, "Please Enter Password",Toast.LENGTH_LONG).show();

        }

        else {
            // sucess message
            progressDialog.setMessage("user signing in..........");
            progressDialog.show();


            myAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  progressDialog.dismiss();

                  if (task.isSuccessful()){
                      finish();

                      Intent gotoprofile= new Intent(Login.this, Profile.class);
                      startActivity(gotoprofile);
                  }

                  else {
                      Toast.makeText(Login.this, "login issues plz try again",Toast.LENGTH_LONG).show();

                      Toast.makeText(Login.this, "Verify Internet connection",Toast.LENGTH_LONG).show();

                  }
                }
            });


        }




    }

}
