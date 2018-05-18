package anderson.app.festafimdeano.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import anderson.app.festafimdeano.R;
import anderson.app.festafimdeano.constants.FimDeAnoConstants;
import anderson.app.festafimdeano.util.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

       // getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticepate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticepate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.check_participate){
            if (this.mViewHolder.checkParticepate.isChecked()){
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WILL_GO);
            } else {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WONT_GO);
            }
        }
    }

    private void loadDataFromActivity(){
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            String presence = extras.getString(FimDeAnoConstants.PRESENCE);
            if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)){
                this.mViewHolder.checkParticepate.setChecked(true);
            } else {
                this.mViewHolder.checkParticepate.setChecked(false);
            }
        }
    }

    private static class ViewHolder{
        CheckBox checkParticepate;
    }

}
