package com.adapter.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.fragments.app.PurchaseFragment;
import com.github.captain_miao.recyclerviewutils.listener.OnRecyclerItemClickListener;
import com.suvidha.app.R;


import java.util.List;


public class SimpleExpandableAdapter extends ExpandableRecyclerAdapter<SimpleExpandableAdapter.SimpleParentViewHolder, SimpleExpandableAdapter.SimpleChildViewHolder> implements OnRecyclerItemClickListener {

    private LayoutInflater mInflater;

    Context c;
    DrawerLayout drawer;
    public SimpleExpandableAdapter(Context context, List<ParentListItem> itemList, DrawerLayout drawer) {
        super(itemList);
        mInflater = LayoutInflater.from(context);
        c = context;
        this.drawer = drawer;
    }

    @Override
    public SimpleParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.group_view_holder, viewGroup, false);
        return new SimpleParentViewHolder(view);
    }

    @Override
    public SimpleChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.sub_cat_viewholder, viewGroup, false);
        SimpleChildViewHolder viewHolder =  new SimpleChildViewHolder(view);
        viewHolder.setOnRecyclerItemClickListener(this);
        viewHolder.addOnItemViewClickListener();
        //viewHolder.addOnViewClickListener(viewHolder.mCheckBox);
        return viewHolder;
    }

    @Override
    public void onBindParentViewHolder(SimpleParentViewHolder parentViewHolder, final int position, ParentListItem parentListItem) {
        SimpleParentItem simpleParentItem = (SimpleParentItem) parentListItem;
      //  Common.setSemiBold(c,parentViewHolder.mTvTitle);

        parentViewHolder.mTvTitle.setText(simpleParentItem.getTitle());
        if(simpleParentItem.getChildItemList() != null){
            parentViewHolder.mParentDropDownArrow.setVisibility(View.VISIBLE);
        }
        else{
            parentViewHolder.mParentDropDownArrow.setVisibility(View.GONE);

        }
/*
        parentViewHolder.mParentDropDownArrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              */
/*  Products f = new Products();
                Bundle b = new Bundle();
                b.putString("cat_id",simpleParentItem.getId());
                b.putString("cat_name",simpleParentItem.getTitle());
                b.putString("type","simple");
                f.setArguments(b);
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction().addToBackStack("subcat").replace(R.id.frame, f).commit();
*//*

            }
        });
*/
    }

    @Override
    public void onBindChildViewHolder(SimpleChildViewHolder simpleChildViewHolder, int position, Object childListItem) {
        SimpleChild simpleChild = (SimpleChild) childListItem;
      //  Common.setRegular(c,simpleChildViewHolder.mTvContent);
        simpleChildViewHolder.mTvContent.setText(simpleChild.getTitle());
      //  simpleChildViewHolder.mCheckBox.setChecked(simpleChild.isSolved());
    }

    @Override
    public void onClick(View v, int position) {
        int id = v.getId();
        Object item = getItem(position);

                    int itemPosition = (int) getItemId(position);
                    int parentPosition = getParentPosition(itemPosition);
                    int parentIndex = getParentWrapperIndex(parentPosition);
                    if (item instanceof SimpleChild) {
                     //   Log.e("values",((SimpleChild) item).getCat_id()+"Ak"+((SimpleChild)item).getTitle());
                        PurchaseFragment f = new PurchaseFragment();
                        ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                        drawer.closeDrawer(GravityCompat.START);
                      /*  Products f = new Products();
                        Bundle b = new Bundle();
                        b.putString("cat_id",((SimpleChild) item).getCat_id());
                        b.putString("cat_name",((SimpleChild)item).getTitle());
                        b.putString("type","simple");
                        f.setArguments(b);
                        ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction().addToBackStack("subcat").replace(R.id.frame, f).commit();

*/
                    }
    }

    public class SimpleChildViewHolder extends ChildViewHolder {

        public TextView mTvContent;

        public SimpleChildViewHolder(View itemView) {
            super(itemView);

            mTvContent =  itemView.findViewById(R.id.txt_subcat_name);
        }
    }

    public class SimpleParentViewHolder extends ParentViewHolder {
        private static final float INITIAL_POSITION = 0.0f;
        private static final float ROTATED_POSITION = 180f;
        private final boolean HONEYCOMB_AND_ABOVE = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;

        public TextView mTvTitle;
        public ImageButton mParentDropDownArrow;
      //  public ImageButton mParentDropDownArrow1;


        public SimpleParentViewHolder(View itemView) {
            super(itemView);

            mTvTitle =  itemView.findViewById(R.id.txt_cat_name);
            mParentDropDownArrow = itemView.findViewById(R.id.collapseButton);
         //   mParentDropDownArrow1 = itemView.findViewById(R.id.parent_list_item_expand_arrow1);

            mParentDropDownArrow.setClickable(false);
        }

        @SuppressLint("NewApi")
        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);
            if (!HONEYCOMB_AND_ABOVE) {
                return;
            }

            if (expanded) {
                mParentDropDownArrow.setRotation(ROTATED_POSITION);
            } else {
                mParentDropDownArrow.setRotation(INITIAL_POSITION);
            }
        }
    }
}
