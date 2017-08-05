package ve.needforock.contactsapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends Fragment {


    public AddContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //accedemos a los recursos dentro del layout del fragment
        Button saveButton = (Button) view.findViewById(R.id.addButton);
        final EditText nameET = (EditText) view.findViewById(R.id.nameEt);
        final EditText phoneET = (EditText) view.findViewById(R.id.phoneEt);
        final RadioGroup groupRg = (RadioGroup) view.findViewById(R.id.gruopRg);

        final CheckBox emailCb = (CheckBox) view.findViewById(R.id.mailCb);
        final TextView emailTv = (TextView) view.findViewById(R.id.emailTv);
        final EditText emailET = (EditText) view.findViewById(R.id.emailEt);

        //Creamos la información a pasar entre actividades

        //creamos las acciones de cada vista dentro del fragment
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddContactActivity.class);
                Bundle name = new Bundle();
                Bundle phone = new Bundle();
                Bundle email = new Bundle();
                Bundle emailCheck = new Bundle();
                Bundle group = new Bundle();
                name.putString("NOMBRE", nameET.getText().toString());
                phone.putString("Telefono", phoneET.getText().toString());
                email.putString("Email", emailET.getText().toString());

                int id = groupRg.getCheckedRadioButtonId();

                if (id!=-1){
                    RadioButton radioButton = (RadioButton) groupRg.findViewById(id);
                    String answer = radioButton.getText().toString();
                    group.putString("GROUP",answer);
                }else{
                    group.putString("GROUP","Sin Grupo");
                }

                intent.putExtras(group);
                intent.putExtras(name);
                intent.putExtras(phone);
                intent.putExtras(email);
                if(nameET.getText().toString().equals("") && phoneET.getText().toString().equals("") && emailET.getText().toString().equals("")&&id==-1) {
                    Snackbar.make(v, "Debes Ingresar informacion", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else if (emailCb.isChecked()&& emailET.getText().toString().equals("")) {
                    Snackbar.make(v, "Debes Ingresar Correo", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (id==-1){
                    Snackbar.make(v, "Debes Seleccionar Grupo", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else {
                    startActivity(intent);
                }
            }
        });


       emailCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked){
                   emailET.setVisibility(View.VISIBLE);
                   emailTv.setVisibility(View.VISIBLE);
               }else{
                   emailET.setVisibility(View.GONE);
                   emailTv.setVisibility(View.GONE);
                   emailET.setText("");
               }

           }
       });

    }


}
