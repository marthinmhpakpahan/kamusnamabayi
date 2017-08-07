package ayusiahaan.kamusnamabayi;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    private LinearLayout lnrExit, lnrSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        lnrSearch = (LinearLayout)findViewById(R.id.lnrSearch);
        lnrExit = (LinearLayout)findViewById(R.id.lnrExit);

        lnrSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lnrExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogConfirmExit();
            }
        });
    }

    Dialog dialog;
    void showDialogConfirmExit(){
        dialog = new Dialog(MenuActivity.this);

        dialog.setContentView(R.layout.confirm_exit_dialog);

        final Button btnYes = (Button) dialog.findViewById(R.id.btnYes);
        final Button btnNo = (Button) dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
