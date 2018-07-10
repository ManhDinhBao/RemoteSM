package com.ibs.android.remotesm;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import static com.ibs.android.remotesm.settingActivity.EXTRA_SETTING_TYPE;
public class LocalActivity extends AppCompatActivity {
    Layout setting;
    CardView cardURL;
    CardView cardUserName;
    CardView cardPass;
    EditText editText;
    TextView txtTitle;
    Button btnOK,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        Intent i=getIntent();
        String type=i.getExtras().getString(EXTRA_SETTING_TYPE);

        if (type.equals("local"))
            setTitle("Local setting");
        else
            if (type.equals("remote")) {
                setTitle("Remote setting");
                TextView txt=(TextView)findViewById(R.id.txtDemo);
                txt.setText("Remote server URL");
            }

        cardUserName=(CardView)findViewById(R.id.card_User);
        cardUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtSubUser=(TextView)findViewById(R.id.txtUserValues);
                callDialog("Username",txtSubUser.getText().toString());
            }
        });

        cardPass=(CardView)findViewById(R.id.card_Pass);
        cardPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtSubPass=(TextView)findViewById(R.id.txtPassValues);
                callDialog("Password",txtSubPass.getText().toString());
            }
        });

        cardURL=(CardView)findViewById(R.id.card_URL);
        cardURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtSubURL=(TextView)findViewById(R.id.txtSubURL);
                callDialog("URL",txtSubURL.getText().toString());
            }
        });


    }
    private void callDialog(String dialogTitle,String values)
    {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(LocalActivity.this);
        View mView=getLayoutInflater().inflate(R.layout.activity_dialog,null);

        txtTitle=(TextView)mView.findViewById(R.id.txtDlgTitle);
        editText=(EditText)mView.findViewById(R.id.editDlgValue);
        btnOK=(Button)mView.findViewById(R.id.btnOK);
        btnCancel=(Button)mView.findViewById(R.id.btnCancel);

        txtTitle.setText(dialogTitle);
        editText.setText(values);

        mBuilder.setView(mView);
        final AlertDialog dialog=mBuilder.create();
        dialog.show();

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtTitle.getText().equals("Username"))
                {
                    TextView txtSubUser=(TextView)findViewById(R.id.txtUserValues);
                    txtSubUser.setText(editText.getText());
                    dialog.dismiss();
                }
                else if (txtTitle.getText().equals("Password"))
                {
                    TextView txtSubPass=(TextView)findViewById(R.id.txtPassValues);
                    txtSubPass.setText(editText.getText());
                    dialog.dismiss();
                }
                else if (txtTitle.getText().equals("URL"))
                {
                    TextView txtSubURL=(TextView)findViewById(R.id.txtSubURL);
                    txtSubURL.setText(editText.getText());
                    dialog.dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
