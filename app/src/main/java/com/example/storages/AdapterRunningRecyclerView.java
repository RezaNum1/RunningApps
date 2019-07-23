package com.example.storages;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterRunningRecyclerView extends RecyclerView.Adapter<AdapterRunningRecyclerView.ViewHolder> {
    private ArrayList<Running> daftarRunning;
    private Context context;
    FirebaseDataListener listener;

    public AdapterRunningRecyclerView(ArrayList<Running> runnings, Context ctx){
        daftarRunning = runnings;
        context = ctx;
        listener = (FirebaseDataListener)ctx;
    }

    @NonNull
    @Override
    public AdapterRunningRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_run, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRunningRecyclerView.ViewHolder holder, final int position) {
        final String pos = daftarRunning.get(position).getPosition();
        final String des = daftarRunning.get(position).getDestination();
        final String jar = daftarRunning.get(position).getJarak();
        final String wak = daftarRunning.get(position).getWaktutempuh();
        final String tan = daftarRunning.get(position).getTanggal();
        System.out.println("RUNNING DATA one by one "+position+ daftarRunning.size());

        holder.tvPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(FirebaseDBReadSingleActivity.getActIntent((Activity) context).putExtra("data", daftarRunning.get(position)));
                }
        });

        holder.tvDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(FirebaseDBReadSingleActivity.getActIntent((Activity) context).putExtra("data", daftarRunning.get(position)));
            }
        });

        holder.tvWaktutempuh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(FirebaseDBReadSingleActivity.getActIntent((Activity) context).putExtra("data", daftarRunning.get(position)));
            }
        });

        holder.tvPosition.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Action List");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        context.startActivity(FirebaseDBCreateActivity.getActIntent((Activity) context).putExtra("data", daftarRunning.get(position)));
                    }
                });

                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        listener.onDeleteRunning(daftarRunning.get(position), position);
                    }
                });
                return true;
            }
        });

        holder.tvPosition.setText(pos);
        holder.tvDestination.setText(des);
        holder.tvJarak.setText(jar);
        holder.tvWaktutempuh.setText(wak);
        holder.tvTanggal.setText(tan);
    }

    @Override
    public int getItemCount() {
        return daftarRunning.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPosition, tvDestination, tvJarak, tvWaktutempuh, tvTanggal;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvPosition = (TextView) v.findViewById(R.id.tv_position);
            tvDestination = (TextView) v.findViewById(R.id.tv_destination);
            tvJarak = (TextView) v.findViewById(R.id.tv_jarak);
            tvWaktutempuh = (TextView) v.findViewById(R.id.tv_waktutempuh);
            tvTanggal = (TextView) v.findViewById(R.id.tv_tanggal);
        }
    }

    public interface FirebaseDataListener{
        void onDeleteRunning(Running running, int position);
    }
}
