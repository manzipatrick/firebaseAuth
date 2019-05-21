package manzi.ur.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databasereference;


    Button btnsignout,btnlist,btnsave;
    TextView etinformation,etname,etaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        databasereference= FirebaseDatabase.getInstance().getReference();

        etname= (EditText)findViewById(R.id.etname);
        etaddress=(EditText)findViewById(R.id.etaddress);

        btnsave= (Button)findViewById(R.id.btnsave) ;
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUserInformation();

            }
        });


        btnlist= (Button)findViewById(R.id.btnlist) ;

        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,Arraylist.class);
                startActivity(intent);





            }
        });

        etinformation= (TextView)findViewById(R.id.etinformation);


        firebaseAuth= FirebaseAuth.getInstance();

        btnsignout= (Button)findViewById(R.id.btnsignout);

        //FirebaseUser user1= firebaseAuth.getCurrentUser();

       // etinformation.setText("Welcome " + user1.getEmail() );


        btnsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                firebaseAuth.signOut();
                Intent i= new Intent(Profile.this, Login.class);
                startActivity(i);
            }
        });
    }

    public void saveUserInformation(){
        String name= etname.getText().toString().trim();
        String address= etaddress.getText().toString().trim();

        if (name.isEmpty() && address.isEmpty()){

            Toast.makeText(this,"Both Field cant be empty",Toast.LENGTH_LONG).show();

        }

        else {

            UserInformation userinformation = new UserInformation(name, address);
            FirebaseUser user = firebaseAuth.getCurrentUser();

            databasereference.child(user.getUid()).setValue(userinformation);

            Toast.makeText(this, "Information saved successfully", Toast.LENGTH_LONG).show();
        }

    }
}
