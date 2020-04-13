package net.kathir.firebasecrud.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.kathir.firebasecrud.EditActivity;
import net.kathir.firebasecrud.R;
import net.kathir.firebasecrud.model.ListModel;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    List<ListModel> nodeslist;
    private Context context;

    public NotesAdapter(List<ListModel> nodeslist, Context context) {
        this.nodeslist = nodeslist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ListModel data=nodeslist.get(position);
        holder.title.setText(data.getTitle());
        holder.desc.setText(data.getDesc());

    }

    @Override
    public int getItemCount() {
        return nodeslist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title,desc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            desc=itemView.findViewById(R.id.desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListModel listdata=nodeslist.get(getAdapterPosition());
                    Intent i=new Intent(context, EditActivity.class);
                    i.putExtra("id",listdata.id);
                    i.putExtra("title",listdata.title);
                    i.putExtra("desc",listdata.desc);
                    context.startActivity(i);
                }
            });
        }
    }
}
