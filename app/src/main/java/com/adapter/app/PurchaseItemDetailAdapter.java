package com.adapter.app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.github.captain_miao.recyclerviewutils.listener.OnRecyclerItemClickListener;
import com.model.login.purchase.item.Table2;
import com.suvidha.app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseItemDetailAdapter extends ExpandableRecyclerAdapter<PurchaseItemDetailAdapter.SimpleParentViewHolder, PurchaseItemDetailAdapter.SimpleChildViewHolder> implements OnRecyclerItemClickListener {

    private LayoutInflater mInflater;

    Context c;
    String value = "";
    int expandValue  = -1;
    List<Table2> listRate;

    public PurchaseItemDetailAdapter(Context context, List<ParentListItem> itemList, List<Table2> listRate) {
        super(itemList);
        mInflater = LayoutInflater.from(context);
        c = context;
        this.listRate = listRate;
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
        final SimpleParentItem simpleParentItem = (SimpleParentItem) parentListItem;
        //  Common.setSemiBold(c,parentViewHolder.mTvTitle);
        parentViewHolder.txtItemNo.setText(simpleParentItem.getItemCode());
        parentViewHolder.txtItemName.setText(simpleParentItem.getItemName());
        parentViewHolder.txtItemQty.setText(String.valueOf(simpleParentItem.getItemQuantity()));
        parentViewHolder.txtItemPrice.setText(String.valueOf(simpleParentItem.getItemRate()));


       // parentViewHolder.txtItemNo.setPaintFlags(parentViewHolder.txtItemNo.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

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
        parentViewHolder.layDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* parentViewHolder.layBtns.setVisibility(View.VISIBLE);
                parentViewHolder.lineBtns.setVisibility(View.VISIBLE);*/
                if(expandValue == position){
                    expandValue = -1;
                    parentViewHolder.layBtns.setVisibility(View.GONE);
                    parentViewHolder.lineBtns.setVisibility(View.GONE);
                    parentViewHolder.imgDrop.setImageResource(R.drawable.ic_expand_more_black_24dp);
                    parentViewHolder.imgDrop.setColorFilter(ContextCompat.getColor(c, R.color.dark_blue), android.graphics.PorterDuff.Mode.SRC_IN);
                    value = "delivery1";
                    parentViewHolder.layDelivery.setBackgroundColor(Color.parseColor("#ffffff"));
                    parentViewHolder.layStock.setBackgroundColor(Color.parseColor("#ffffff"));
                    parentViewHolder.txtDel.setTextColor(Color.parseColor("#000000"));
                    parentViewHolder.txtStock.setTextColor(Color.parseColor("#000000"));

                    expandParent(position);
                    collapseParent(position);
                }
                else {
                    expandValue = position;
                    parentViewHolder.layBtns.setVisibility(View.VISIBLE);
                    parentViewHolder.lineBtns.setVisibility(View.VISIBLE);
                    parentViewHolder.imgDrop.setImageResource(R.drawable.ic_expand_less_black_24dp);
                    parentViewHolder.imgDrop.setColorFilter(ContextCompat.getColor(c, R.color.dark_blue), android.graphics.PorterDuff.Mode.SRC_IN);

                }
               // notifyDataSetChanged();
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
                    expandParent(position);
                    collapseParent(position);
                }else{
                    parentViewHolder.layDelivery.setBackgroundColor(Color.parseColor("#044A6C"));
                    parentViewHolder.layStock.setBackgroundColor(Color.parseColor("#ffffff"));
                    parentViewHolder.txtDel.setTextColor(Color.parseColor("#FFFFFF"));
                    parentViewHolder.txtStock.setTextColor(Color.parseColor("#000000"));
                    value = "delivery";
                    collapseParent(position);
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
                    expandParent(position);
                    collapseParent(position);
                }else{
                    parentViewHolder.layStock.setBackgroundColor(Color.parseColor("#044A6C"));
                    parentViewHolder.layDelivery.setBackgroundColor(Color.parseColor("#ffffff"));
                    parentViewHolder.txtStock.setTextColor(Color.parseColor("#FFFFFF"));
                    parentViewHolder.txtDel.setTextColor(Color.parseColor("#000000"));
                    value = "stock";
                    collapseParent(position);
                    expandParent(position);
                }
               // value = "stock";


                //notifyDataSetChanged();
            }
        });
        parentViewHolder.txtRateHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Table2> list = new ArrayList<>();
                Log.e("list",listRate.toString());
                if(listRate != null && listRate.size() > 0){
                    for(int i =0; i<listRate.size();i++){
                     if(listRate.get(i).getItemid().toString().equals(simpleParentItem.getItemId())){
                         list.add(listRate.get(i));
                     }
                    }
                    if(list != null && list.size() > 0) {
                        openDialog(c, list);
                    }
                }

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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
            Date d = null;
            if(simpleChild.getDeliverydate() != null && !simpleChild.getDeliverydate().equals("")) {
                try {
                    d = sdf.parse(simpleChild.getDeliverydate());
                    String formattedTime = output.format(d);
                    simpleChildViewHolder.txtDelDate.setText("Scheduled Dly. : " + formattedTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            simpleChildViewHolder.txtDelQty.setText(simpleChild.getRequiredqty());
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

        public TextView txtDelDate,txtDelQty;
        LinearLayout layStockData;
        RelativeLayout layDeliveryData;

        public SimpleChildViewHolder(View itemView) {
            super(itemView);

            txtDelDate = itemView.findViewById(R.id.txt_date_scheduled);
            txtDelQty = itemView.findViewById(R.id.txt_quantity);
            layStockData = itemView.findViewById(R.id.lay_stock_data);
            layDeliveryData = itemView.findViewById(R.id.lay_delivery_data);
        }
    }
    private void openDialog(Context ctx, List<Table2> list) {

        final Dialog dialog1 = new  Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar);
        dialog1.setContentView(R.layout.history_dialog);
        Button btnClose = dialog1.findViewById(R.id.btn_close);

        RecyclerView recycler_history  =dialog1.findViewById(R.id.recycler_history);
        recycler_history.setLayoutManager(new LinearLayoutManager(ctx));
        recycler_history.setAdapter(new HistoryAdapter(ctx,list));
        dialog1.show();
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

    }
    public class SimpleParentViewHolder extends ParentViewHolder {
        private static final float INITIAL_POSITION = 0.0f;
        private static final float ROTATED_POSITION = 180f;
        private final boolean HONEYCOMB_AND_ABOVE = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;

        public TextView txtItemName,txtItemQty;
        public ImageButton mParentDropDownArrow;
        public LinearLayout layBtns, layClick;
        public View lineBtns;
        RelativeLayout layDelivery,layDrop;
        RelativeLayout layStock;
        TextView txtItemPrice, txtItemNo, txtDel, txtStock,txtRateHistory;
        ImageView imgDrop;

        //  public ImageButton mParentDropDownArrow1;


        public SimpleParentViewHolder(View itemView) {
            super(itemView);

            txtItemName = itemView.findViewById(R.id.txt_item_name);
            txtItemNo = itemView.findViewById(R.id.txt_item_no);
            txtItemQty = itemView.findViewById(R.id.txt_item_qty);
            txtItemPrice = itemView.findViewById(R.id.txt_item_price);

            txtDel = itemView.findViewById(R.id.txt_del);
            txtStock = itemView.findViewById(R.id.txt_stock);
            layBtns = itemView.findViewById(R.id.lay_btns);
            lineBtns = itemView.findViewById(R.id.line_btns);
            layClick = itemView.findViewById(R.id.layClick);
            layDelivery = itemView.findViewById(R.id.btn_delivery_status);


            layStock = itemView.findViewById(R.id.btn_stock_status);
            txtRateHistory = itemView.findViewById(R.id.txt_rate_history);
            layDrop = itemView.findViewById(R.id.lay_click_item);
            imgDrop = itemView.findViewById(R.id.drop_img_item);
            //mParentDropDownArrow = itemView.findViewById(R.id.collapseButton);
            //   mParentDropDownArrow1 = itemView.findViewById(R.id.parent_list_item_expand_arrow1);

            layClick.setClickable(true);
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
