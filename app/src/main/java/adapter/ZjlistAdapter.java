package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a17524.myapplication.MusicpalyActivity;
import com.example.a17524.myapplication.R;

import java.io.Serializable;
import java.util.List;

import util.data;

/**
 * Created by 17524 on 2019/10/30.
 */

public class ZjlistAdapter extends RecyclerView.Adapter<ZjlistAdapter.InnerHolder>{
    private Context mContent;
    TextView textView,textView1;
    private RecyclerView mRv;
    boolean wo=false;
    ImageView icon;
    List<data> mdata;
    View view;
    public ZjlistAdapter(Context context, RecyclerView recyclerView, List<data> mdata){
        this.mContent=context;
        this.mRv=recyclerView;
        this.mdata=mdata;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ZjlistAdapter.InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view=View.inflate(parent.getContext(), R.layout.list_item,null);
        return new ZjlistAdapter.InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(final ZjlistAdapter.InnerHolder holder, int position) {
        Glide.with(mContent).load(mdata.get(position).pic).into(icon);
        textView.setText(mdata.get(position).author);
        textView1.setText(mdata.get(position).title);
        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContent,MusicpalyActivity.class);
                intent.putExtra("position",holder.getAdapterPosition());
                intent.putExtra("mdata",(Serializable)mdata);
                mContent.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        View itemview;
        public InnerHolder(View itemView) {
            super(itemView);
            icon=(ImageView)itemView.findViewById(R.id.list_icon1);
            textView=(TextView)itemView.findViewById(R.id.textView8) ;
            textView1=(TextView)itemView.findViewById(R.id.textView5);
            this.itemview=itemView;
        }
    }
}
