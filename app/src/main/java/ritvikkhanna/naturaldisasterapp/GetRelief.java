package ritvikkhanna.naturaldisasterapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GetRelief extends AppCompatActivity {

    int x=0;
    private Button b_get;
    private Button go;
    private TrackGPS gps;
    double longitude;
    double latitude;
    private TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_relief);
        b_get = (Button)findViewById(R.id.button1);
        show = (TextView) findViewById(R.id.res);
        go = (Button) findViewById(R.id.map);
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE);
        go.startAnimation(animation);

        b_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gps = new TrackGPS(GetRelief.this);


                if(gps.canGetLocation()){


                    longitude = gps.getLongitude();
                    latitude = gps .getLatitude();


                    Toast.makeText(getApplicationContext(),"Current Location - \nLongitude:"+Double.toString(longitude)+"\nLatitude:"+Double.toString(latitude),Toast.LENGTH_SHORT).show();
                    if(longitude>75&&longitude<81){
                        if(latitude>11&&latitude<13){
                            show.setText("Nearest Relief Center Is At - CMC,Vellore,TN \n 79.1354N \n 12.9248E");
                            x=0;
                        }
                    }
                    else{
                        show.setText("Nearest Relief Center Is At - CMC,Vellore,TN \n 77.6990E \n 12.9568N");
                        x=1;
                    }
}}});
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent();
                a.setAction(Intent.ACTION_VIEW);
                if(x==1){a.setData(Uri.parse("geo:12.9568,77.6990"));}
                else{a.setData(Uri.parse("geo:12.9248,79.1354"));}
                startActivity(a);
            }
    });
}}
