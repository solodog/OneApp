package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import util.ItemBean;
import com.example.a17524.myapplication.R;
import java.util.List;
import util.DataCleanManager;

/**
 * Created by 17524 on 2019/10/14.
 */

public class StaggerAdapter extends RecyclerView.Adapter<StaggerAdapter.InnerHolder> {
    ImageView icon;
    public Context mContext;
    List<ItemBean> mdata;
    int posi;
    int i;
    DataCleanManager dataCleanManager;
    public StaggerAdapter(List<ItemBean> data,Context mContext,int i){
        this.mContext=mContext;
        this.mdata=data;
        this.i=i;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public StaggerAdapter.InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(),R.layout.stagger_itme,null);
        mContext=parent.getContext();
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(StaggerAdapter.InnerHolder holder,int position) {
        final String po=String.valueOf((i*10)+position);
        Log.d("w",""+po);
        this.posi=position;
        Glide.with(mContext).load(mdata.get(position).text).signature(new StringSignature(po)).into(icon);
        icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }



    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(View itemView) {
            super(itemView);
            icon=(ImageView) itemView.findViewById(R.id.imageView2);
        }
    }

    public void cleardata(){
        dataCleanManager.clearAllCache(mContext);
    }
}
