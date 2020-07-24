package com.edthedeveloper.happynewyear.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.edthedeveloper.happynewyear.R;
import com.edthedeveloper.happynewyear.constant.Happy_New_Year_consts;
import com.edthedeveloper.happynewyear.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public ViewHolder mViewHolder = new ViewHolder();

    private SecurityPreferences mSecurityPrefereces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPrefereces = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.check_participate){
            if(this.mViewHolder.checkParticipate.isChecked()){
                //salvar confirmação

                this.mSecurityPrefereces.storeString(Happy_New_Year_consts.PRESENCE_KEY,Happy_New_Year_consts.CONFIRMATION_YES);
            } else{
                //salvar ausencia
                this.mSecurityPrefereces.storeString(Happy_New_Year_consts.PRESENCE_KEY,Happy_New_Year_consts.CONFIRMATION_NO);
            }
        }
    }

    private void loadDataFromActivity(){
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            String presence = extras.getString(Happy_New_Year_consts.PRESENCE_KEY);
            if(presence != null && presence.equals(Happy_New_Year_consts.CONFIRMATION_YES)){
                this.mViewHolder.checkParticipate.setChecked(true);
            } else{
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder{
        CheckBox checkParticipate;
    }
}
