package ve.needforock.contactsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AddContactActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        TextView name = (TextView) findViewById(R.id.newContactNameTv);
        TextView phone = (TextView) findViewById(R.id.newContactPhoneTv);
        TextView email = (TextView) findViewById(R.id.newContactEmailTv);
        TextView group = (TextView) findViewById(R.id.groupTv);
        Bundle bundle = this.getIntent().getExtras();



        name.setText(bundle.getString("NOMBRE"));
        phone.setText(bundle.getString("Telefono"));

        if(bundle.getString("Email").equals(null)||bundle.getString("Email").equals("")){
            email.setVisibility(View.GONE);

        }else{
            email.setText(bundle.getString("Email"));
            email.setVisibility(View.VISIBLE);
        }

        group.setText(bundle.getString("GROUP"));
    }



}
