package com.adapter.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.github.captain_miao.recyclerviewutils.listener.OnRecyclerItemClickListener;
import com.suvidha.app.R;

import java.util.List;

public class PurchaseItemDetailAdapter extends ExpandableRecyclerAdapter<PurchaseItemDetailAdapter.SimpleParentViewHolder, PurchaseItemDetailAdapter.SimpleChildViewHolder> implements OnRecyclerItemClickListener {

    private LayoutInflater mInflater;

    Context c;
    String value = "";

    public PurchaseItemDetailAdapter(Context context, List<ParentListItem> itemList) {
        super(itemList);
        mInflater = LayoutInflater.from(context);
        c = context;
    }

    @Override
    public SimpleParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.item_detail_adapter, viewGroup, false);
        return new SimpleParentViewHolder(view);
    }

    @Override
    public SimpleChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.delivery_schedule, viewGroup, false);
        SimpleChildViewHolder viewHolder = new SimpleChildViewHolder(view);
        viewHolder.setOnRecyclerItemClickListener(this);
        viewHolder.addOnItemViewClickListener();
        //viewHolder.addOnViewClickListener(viewHolder.mCheckBox);
        return viewHolder;
    }

    @Override
    public void onBindParentViewHolder(final SimpleParentViewHolder parentViewHolder, final int position, ParentListItem parentListItem) {
        SimpleParentItem simpleParentItem = (SimpleParentItem) parentListItem;
        //  Common.setSemiBold(c,parentViewHolder.mTvTitle);
        parentViewHolder.txtPrice.setText("@85");
        parentViewHolder.txtItemNo.setPaintFlags(parentViewHolder.txtItemNo.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

//        parentViewHolder.mTvTitle.setText(simpleParentItem.getTitle());
//        if (value.equalsIgnoreCase("delivery")) {
//            parentViewHolder.layDelivery.setBackgroundColor(Color.parseColor("#008577"));
//            parentViewHolder.layStock.setBackgroundColor(Color.parseColor("#ffffff"));
//            parentViewHolder.txtDel.setTextColor(Color.parseColor("#FFFFFF"));
//            parentViewHolder.txtStock.setTextColor(Color.parseColor("#000000"));
//        } else if (value.equalsIgnoreCase("stock")) {
//            parentViewHolder.layStock.setBackgroundColor(Color.parseColor("#008577"));
//            parentViewHolder.layDelivery.setBackgroundColor(Color.parseColor("#ffffff"));
//            parentViewHolder.txtStock.setTextColor(Color.parseColor("#FFFFFF"));
//            parentViewHolder.txtDel.setTextColor(Color.parseColor("#000000"));
//        }
//
        if (simpleParentItem.getChildItemList() != null) {
//            parentViewHolder.mParentDropDownArrow.setVisibility(View.VISIBLE);
        } else {
            //parentViewHolder.mParentDropDownArrow.setVisibility(View.GONE);

        }
        parentViewHolder.layClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentViewHolder.layBtns.setVisibility(View.VISIBLE);
                parentViewHolder.lineBtns.setVisibility(View.VISIBLE);
            }
        });
        parentViewHolder.layDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value.equalsIgnoreCase("delivery")) {
                    value = "delivery1";
                    parentViewHolder.layDelivery.setBackgroundColor(Color.parseColor("#ffffff"));
                    parentViewHolder.layStock.setBackgroundColor(Color.parseColor("#ffffff"));
                    parentViewHolder.txtDel.setTextColor(Color.parseColor("#000000"));
                    parentViewHolder.txtStock.setTextColor(Color.parseColor("#000000"));
                    collapseParent(position);
                }else{
                    value = "delivery";
                    parentViewHolder.layDelivery.setBackgroundColor(Color.parseColor("#008577"));
                    parentViewHolder.layStock.setBackgroundColor(Color.parseColor("#ffffff"));
                    parentViewHolder.txtDel.setTextColor(Color.parseColor("#FFFFFF"));
                    parentViewHolder.txtStock.setTextColor(Color.parseColor("#000000"));
                    expandParent(position);
                }

               // notifyDataSetChanged();
            }
        });
        parentViewHolder.layStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value.equalsIgnoreCase("stock")) {
                    value = "stock1";
                    parentViewHolder.layDelivery.setBackgroundColor(Color.parseColor("#ffffff"));
                    parentViewHolder.layStock.setBackgroundColor(Color.parseColor("#ffffff"));
                    parentViewHolder.txtDel.setTextColor(Color.parseColor("#000000"));
                    parentViewHolder.txtStock.setTextColor(Color.parseColor("#000000"));
                    collapseParent(position);
                }else{
                    value = "stock";
                    parentViewHolder.layStock.setBackgroundColor(Color.parseColor("#008577"));
                    parentViewHolder.layDelivery.setBackgroundColor(Color.parseColor("#ffffff"));
                    parentViewHolder.txtStock.setTextColor(Color.parseColor("#FFFFFF"));
                    parentViewHolder.txtDel.setTextColor(Color.parseColor("#000000"));
                    expandParent(position);
                }
               // value = "stock";

                //notifyDataSetChanged();
            }
        });
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
        // simpleChildViewHolder.mTvContent.setText(simpleChild.getTitle());
        // //  simpleChildViewHolder.mCheckBox.setChecked(simpleChild.isSolved());
        if (value.equalsIgnoreCase("stock")) {
            simpleChildViewHolder.layStockData.setVisibility(View.VISIBLE);
            simpleChildViewHolder.layDeliveryData.setVisibility(View.GONE);
        }
        if (value.equalsIgnoreCase("delivery")) {
            simpleChildViewHolder.layStockData.setVisibility(View.GONE);
            simpleChildViewHolder.layDeliveryData.setVisibility(View.VISIBLE);
        }
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
        LinearLayout layStockData;
        RelativeLayout layDeliveryData;

        public SimpleChildViewHolder(View itemView) {
            super(itemView);

            mTvContent = itemView.findViewById(R.id.txt_deliverydate);
            layStockData = itemView.findViewById(R.id.lay_stock_data);
            layDeliveryData = itemView.findViewById(R.id.lay_delivery_data);
        }
    }

    public class SimpleParentViewHolder extends ParentViewHolder {
        private static final float INITIAL_POSITION = 0.0f;
        private static final float ROTATED_POSITION = 180f;
        private final boolean HONEYCOMB_AND_ABOVE = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;

        public TextView mTvTitle;
        public ImageButton mParentDropDownArrow;
        public LinearLayout layBtns, layClick;
        public View lineBtns;
        RelativeLayout layDelivery;
        RelativeLayout layStock;
        TextView txtPrice, txtItemNo, txtDel, txtStock;

        //  public ImageButton mParentDropDownArrow1;


        public SimpleParentViewHolder(View itemView) {
            super(itemView);

            mTvTitle = itemView.findViewById(R.id.txt_item_code);
            txtDel = itemView.findViewById(R.id.txt_del);
            txtStock = itemView.findViewById(R.id.txt_stock);
            layBtns = itemView.findViewById(R.id.lay_btns);
            lineBtns = itemView.findViewById(R.id.line_btns);
            layClick = itemView.findViewById(R.id.layClick);
            layDelivery = itemView.findViewById(R.id.btn_delivery_status);
            txtPrice = itemView.findViewById(R.id.txt_item_price);
            txtItemNo = itemView.findViewById(R.id.txt_item_no);
            layStock = itemView.findViewById(R.id.btn_stock_status);
            //mParentDropDownArrow = itemView.findViewById(R.id.collapseButton);
            //   mParentDropDownArrow1 = itemView.findViewById(R.id.parent_list_item_expand_arrow1);

            layClick.setClickable(false);
        }

        @SuppressLint("NewApi")
        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);
            if (!HONEYCOMB_AND_ABOVE) {
                return;
            }

            if (expanded) {
                // mParentDropDownArrow.setRotation(ROTATED_POSITION);
            } else {
                // mParentDropDownArrow.setRotation(INITIAL_POSITION);
            }
        }
    }
}
