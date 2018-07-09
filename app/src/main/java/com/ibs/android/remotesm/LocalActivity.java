package com.ibs.android.remotesm;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LocalActivity extends AppCompatActivity {
    Layout setting;
    CardView cardLocal;
    CardView cardUserName;
    CardView cardPass;
    EditText editText;
    TextView txtTitle;
    Button btnOK,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        cardUserName=(CardView)findViewById(R.id.card_User);
        cardUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(LocalActivity.this);
                View mView=getLayoutInflater().inflate(R.layout.activity_dialog,null);

                txtTitle=(TextView)findViewById(R.id.txtDlgTitle);
                editText=(EditText)findViewById(R.id.editDlgValue);
                btnOK=(Button)findViewById(R.id.btnOK);
                btnCancel=(Button)findViewById(R.id.btnCancel);

                txtTitle.setText("Username");

                mBuilder.setView(mView);
                AlertDialog dialog=mBuilder.create();
                dialog.show();
            }
        });

    }
}
