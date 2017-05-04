package ritvikkhanna.naturaldisasterapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Information extends AppCompatActivity {
    private TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        t1 = (TextView)findViewById(R.id.edt1);
        t2 = (TextView)findViewById(R.id.edt2);
        t3 = (TextView)findViewById(R.id.edt3);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childRef = rootRef.child("Information");
        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot result){
                List<String> lst = new ArrayList<String>(); // Result will be holded Here
                for(DataSnapshot dsp : result.getChildren()){
                    lst.add(String.valueOf(dsp.getValue())); //add result into array list
                }
                //NOW YOU HAVE ARRAYLIST WHICH HOLD RESULTS

                t1.setText(lst.get(1));
                t2.setText(lst.get(2));
                t3.setText(lst.get(0));

            }

            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(),"Failed",Toast.LENGTH_LONG).show();

            }
    });
}
}
