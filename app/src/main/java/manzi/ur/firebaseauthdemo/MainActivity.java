package manzi.ur.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText etemail, etpassword;
    Button btnlogin, btnregister;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        etemail = (EditText)findViewById(R.id.etemail);
        etpassword = (EditText)findViewById(R.id.etpassword);
        btnlogin= (Button)findViewById(R.id.btnlogin);
        btnregister= (Button)findViewById(R.id.btnregister);

        progressDialog = new ProgressDialog(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
            }
        });
    }

    public void registeruser(){

        String email= etemail.getText().toString().trim();
        String password= etpassword.getText().toString().trim();

        if (email.isEmpty() ){
            // Email is impty
            Toast.makeText(MainActivity.this, "Please Enter Email",Toast.LENGTH_LONG).show();
        }

        else if (password.isEmpty()){
            // Password is impty
            Toast.makeText(MainActivity.this, "Please Enter Password",Toast.LENGTH_LONG).show();

        }

        else {
            // sucess message
            progressDialog.setMessage("user registering..........");
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();

                    if (task.isSuccessful()){

                        Toast.makeText(MainActivity.this, "user registered successfully",Toast.LENGTH_LONG).show();

                    }

                    else {

                        Toast.makeText(MainActivity.this, "could not register please try again",Toast.LENGTH_LONG).show();
                    }

                }
            });





        }
    }
}
