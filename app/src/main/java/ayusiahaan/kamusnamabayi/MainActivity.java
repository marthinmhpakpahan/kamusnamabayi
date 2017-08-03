package ayusiahaan.kamusnamabayi;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    EditText editTextSearch;

    List<BabyModel> models, updatedDataContent;

    BabyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        editTextSearch = (EditText)findViewById(R.id.editTextSearch);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Created By. Ayu Siahaan", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                showDialogHelp();
            }
        });

        loadData();

        editTextSearch.addTextChangedListener(titleWatcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    Dialog dialog;
    void showDialogHelp(){
        dialog = new Dialog(MainActivity.this);

        dialog.setContentView(R.layout.detail_help);

        final Button btnClose = (Button) dialog.findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    void loadData(){
        models = new ArrayList<>();

        InputStream inputStream = MainActivity.this.getResources().openRawResource(R.raw.data);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;

        try {
            while (( line = buffreader.readLine()) != null) {
                String name="", description="";
                Log.e("content", line);
                name = line.split("-")[0].trim();
                description = line.split("-")[1].trim();
                BabyModel model = new BabyModel();
                model.setName(name);
                model.setDefenition(description);
                models.add(model);
            }
        } catch (IOException e) {
            //
        }

        adapter = new BabyAdapter(MainActivity.this, models);
        RecyclerView.LayoutManager mLayoutImageManager = new LinearLayoutManager(MainActivity.this);
        recycler_view.setLayoutManager(mLayoutImageManager);
        recycler_view.setAdapter(adapter);
    }

    private final TextWatcher titleWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updatedDataContent = new ArrayList<BabyModel>();
            for (int i=0; i<models.size(); i++) {
                if (models.get(i).getName().toLowerCase().contains(editTextSearch.getText().toString().toLowerCase())
                        || models.get(i).getDefenition().toLowerCase().contains(editTextSearch.getText().toString().toLowerCase())) {
                    Log.e("KeyListener", "found");
                    updatedDataContent.add(models.get(i));
                }
            }
            Log.e("KeyListener", "Size new: "+updatedDataContent.size());
            adapter = new BabyAdapter(MainActivity.this, updatedDataContent);
            recycler_view.setAdapter(adapter);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
