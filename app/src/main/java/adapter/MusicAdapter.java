package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a17524.myapplication.R;
import com.example.a17524.myapplication.ZjymActivity;

import java.io.Serializable;
import java.util.List;

import jp.wasabeef.glide.transformations.CropSquareTransformation;
import util.ItemBean;
import util.ItemBean_music;
import util.listdatas;

/**
 * Created by 17524 on 2019/10/20.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.InnerHolder> {
    private Context mContent;
    private List<listdatas> itemBeen;
    ImageView icon;
    TextView text;
    public MusicAdapter(Context context, List<listdatas> mdata){
        this.itemBeen=mdata;
        this.mContent=context;
    }

    @Override
    public MusicAdapter.InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.music_itme,null);
        return new MusicAdapter.InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(final InnerHolder holder, int position) {
        Glide.with(mContent).load(itemBeen.get(position).bg).centerCrop()
                .bitmapTransform(new CropSquareTransformation(mContent))
                .into(icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContent, ZjymActivity.class);
                intent.putExtra("position",holder.getAdapterPosition());
                mContent.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemBeen.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(View itemView) {
            super(itemView);
            icon=(ImageView)itemView.findViewById(R.id.imageView5);
        }
    }
}
