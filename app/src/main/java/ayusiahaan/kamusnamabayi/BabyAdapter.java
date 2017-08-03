package ayusiahaan.kamusnamabayi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 8/3/2017.
 */

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.MyViewHolder> {
    private List<BabyModel> models;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textDescription;

        public MyViewHolder(View view) {
            super(view);
            textName = (TextView) view.findViewById(R.id.textName);
            textDescription = (TextView) view.findViewById(R.id.textDescription);
        }
    }

    public BabyAdapter(Context context, List<BabyModel> param_models) {
        this.context = context;
        this.models = param_models;
    }

    public void updateItem(List<BabyModel> new_models) {
        models.clear();
        models = new_models;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_baby, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final BabyModel line = models.get(position);
        holder.textName.setText(line.getName());
        holder.textDescription.setText(line.getDefenition());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDetail(context, line.getName(), line.getDefenition());
            }
        });
    }

    Dialog dialog;
    public void showDialogDetail(Context context, String name, String detail){
        dialog = new Dialog(context);

        dialog.setContentView(R.layout.detail_dialog);
        dialog.setTitle("Detail");

        final TextView textName = (TextView) dialog.findViewById(R.id.textName);
        final TextView textDescription = (TextView) dialog.findViewById(R.id.textDescription);
        final Button btnClose = (Button) dialog.findViewById(R.id.btnClose);

        textName.setText(name);
        textDescription.setText(detail);
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

    @Override
    public int getItemCount() {
        return models.size();
    }
}