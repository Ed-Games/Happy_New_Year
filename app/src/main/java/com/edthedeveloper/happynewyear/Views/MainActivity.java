package com.edthedeveloper.happynewyear.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.edthedeveloper.happynewyear.R;
import com.edthedeveloper.happynewyear.constant.Happy_New_Year_consts;
import com.edthedeveloper.happynewyear.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder  = new ViewHolder();
    private SecurityPreferences mSecurityPrefereces;
    private static SimpleDateFormat SIMPLE_DATE_FORMATE = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPrefereces = new SecurityPreferences(this);

        this.mViewHolder.TextToday = findViewById(R.id.text_today);
        this.mViewHolder.DaysLeft = findViewById(R.id.days_left);
        this.mViewHolder.ButtonConfirm = findViewById(R.id.buton_confirm);

        this.mViewHolder.ButtonConfirm.setOnClickListener(this);


        //dia de hoje
        this.mViewHolder.TextToday.setText(SIMPLE_DATE_FORMATE.format(Calendar.getInstance().getTime()));

        //dias restantes
        String daysleft = String.format("%s %s",String.valueOf(this.getDaysLeft()),getString(R.string.dias));
        this.mViewHolder.DaysLeft.setText(daysleft);

       // verifyPresence();


    }


    @Override
    protected void onResume() {
        super.onResume();
        verifyPresence();
    }




    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buton_confirm){

            String presence =this.mSecurityPrefereces.getStoredString(Happy_New_Year_consts.PRESENCE_KEY);

            Intent intent = new Intent(this, DetailsActivity.class);

            intent.putExtra(Happy_New_Year_consts.PRESENCE_KEY,presence);

            startActivity(intent);
        }
    }

    private void verifyPresence(){
        String presence =this.mSecurityPrefereces.getStoredString(Happy_New_Year_consts.PRESENCE_KEY);

        if(presence.equals("")){
            this.mViewHolder.ButtonConfirm.setText(getString(R.string.nao_confirmado));
        } else if (presence.equals(Happy_New_Year_consts.CONFIRMATION_YES)){
            this.mViewHolder.ButtonConfirm.setText(getString(R.string.sim));
        }  else if (presence.equals(Happy_New_Year_consts.CONFIRMATION_NO)){
            this.mViewHolder.ButtonConfirm.setText(getString(R.string.nao));
        }
    }

    private int getDaysLeft (){
        //data de hoje
        Calendar calendartoday = Calendar.getInstance();
        int today = calendartoday.get(Calendar.DAY_OF_YEAR);

        //data maxima do ano
        Calendar calendarLastDay = Calendar.getInstance();
        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax - today;
    }

    private static class ViewHolder{
        TextView TextToday;
        TextView DaysLeft;
        Button ButtonConfirm;
    }
}
