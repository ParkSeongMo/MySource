package com.example.listpopup;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.listpopup.R;

public class Custom_ListAdapter extends ArrayAdapter<ListItem> {

	private final String TAG = "Custom_ListAdapter";
	
	private Activity mContext;
	private int mLayoutId;
	private ArrayList<ListItem> mArryList;
	private boolean mIsVisibleChk;
	
	public Custom_ListAdapter(Activity context, int layout, ArrayList<ListItem> arryList) {
		super(context, layout, arryList);
		// TODO Auto-generated constructor stub
		
		mLayoutId = layout;
		mContext = context;
		mArryList = arryList;
	}

	public void setVisibleCheckBox(boolean isVisible){
		
		mIsVisibleChk = isVisible;
		notifyDataSetInvalidated();
	}
	
	public boolean isVisibleCheckBox(){
		
		return mIsVisibleChk;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder viewHolder;
		View view= convertView;
		
		if(view==null){
			LayoutInflater inflater = mContext.getLayoutInflater();
			view = inflater.inflate(mLayoutId, null, true);
			viewHolder = new ViewHolder();			
			viewHolder.tv = (TextView) view.findViewById(R.id.tvCommLstTitle);
			viewHolder.chk = (CheckBox) view.findViewById(R.id.chkCommLst);			
			view.setTag(viewHolder);
		}else viewHolder = (ViewHolder)view.getTag();
		
		setControl(viewHolder, position);

		Log.d(TAG, String.format("getView() position:%d, checked:%s", position, mArryList.get(position).isChecked()));
		return view;
	}
	
	private void setControl(ViewHolder viewHolder, int position){
		
		ListItem item = mArryList.get(position);
		
		viewHolder.tv.setText(item.getText());
		viewHolder.chk.setChecked(item.isChecked());
		viewHolder.chk.setTag(position);
		viewHolder.chk.setVisibility(mIsVisibleChk?View.VISIBLE:View.GONE);
		viewHolder.chk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Object obj = v.getTag();
				
				if(obj!=null) {
					CheckBox chk = (CheckBox) v;
					int position = (Integer) obj;
					mArryList.get(position).setChecked(chk.isChecked());
					Log.d(TAG, String.format("setOnClickListener() position:%d, checked:%s", position, mArryList.get(position).isChecked()));
				}
			}
		});
	}
	
	static class ViewHolder{
		public TextView tv;
		public CheckBox chk;
	}

	
}
