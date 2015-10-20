package tutorial.com.felix.manyfeatures;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by felix on 10/20/2015.
 */
public class DrawerItemAdapter extends RecyclerView.Adapter<DrawerItemAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<DrawerItem> items = Collections.emptyList();

    public DrawerItemAdapter(Context context, List<DrawerItem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public DrawerItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.drawer_item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(DrawerItemAdapter.MyViewHolder holder, int position) {
        DrawerItem currentItem = items.get(position);
        holder.getmIcon().setImageResource(currentItem.getIconId());
        holder.getmTitle().setText(currentItem.getTitle());
        holder.getmSubTitle().setText(currentItem.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mTitle;
        private TextView mSubTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.drawer_icon);
            mTitle = (TextView) itemView.findViewById(R.id.drawer_title);
            mSubTitle = (TextView) itemView.findViewById(R.id.drawer_sub_title);
        }

        public ImageView getmIcon() {
            return mIcon;
        }

        public void setmIcon(ImageView mIcon) {
            this.mIcon = mIcon;
        }

        public TextView getmTitle() {
            return mTitle;
        }

        public void setmTitle(TextView mTitle) {
            this.mTitle = mTitle;
        }

        public TextView getmSubTitle() {
            return mSubTitle;
        }

        public void setmSubTitle(TextView mSubTitle) {
            this.mSubTitle = mSubTitle;
        }
    }
}
