package ritvikkhanna.naturaldisasterapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class Details extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextNum;
    private Button buttonSave;
    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        buttonSave = (Button) findViewById(R.id.button1);
        editTextName = (EditText) findViewById(R.id.edt1);
        editTextNum = (EditText) findViewById(R.id.edt2);
        res = (TextView) findViewById(R.id.txtavail);



        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Creating firebase object
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                      DatabaseReference childRef=  rootRef.child("Casualties").child(editTextName.getText().toString());
                 final DatabaseReference shRef =
                        rootRef.child("Shelters").child("Shelter Home 1");
                childRef.setValue(editTextNum.getText().toString());
                shRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String num = editTextNum.getText().toString();
                        String data = String.valueOf(dataSnapshot.getValue());
                        String home = String.valueOf(dataSnapshot.getKey());
                        if(Integer.parseInt(num)<Integer.parseInt(data)){
                            res.setText("Can Provide Accommodation In "+home+"!");
                            shRef.setValue(Integer.toString(Integer.parseInt(data)-Integer.parseInt(num)));

                        }
                        else{
                            res.setText("Cannot Provide Accommodation For All Members");

                        }








                    }
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getBaseContext(),"Failed",Toast.LENGTH_LONG).show();

                                                }});







            }
        });



    }
}
