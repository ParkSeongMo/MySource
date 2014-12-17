package com.example.listpopup;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListDialog extends Dialog {

	private TextView mTvTitle;
	private Button mBtnLeft;

	private Activity mContext;
	private String mTitle;
	private String mBtn = "";

	private ListView mListView;
	private ArrayList<ListItem> mArryList;
	private Custom_ListAdapter mAdapter;

	private View.OnClickListener mDefaultClickListener = new View.OnClickListener() {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			dismiss();
		}
	};

	private View.OnClickListener mLeftClickListener;
	private OnItemClickListener mListItemClickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.popup_list);
		setLayout();
		setClickListener();
	}

	public ListDialog(Activity context) {
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
		mContext = context;
	}

	public ListDialog(Activity context,String title,  View.OnClickListener singleListener) {
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
		this.mTitle = title;
		this.mLeftClickListener = singleListener;
		mContext = context;
	}

	public void setBtnString(String strBtn){
		mBtn = strBtn;
	}

	private void setClickListener() {
		if(mBtnLeft!=null) 
			if(mLeftClickListener!=null) mBtnLeft.setOnClickListener(mLeftClickListener);
			else mBtnLeft.setOnClickListener(mDefaultClickListener);	
	}

	private void setLayout() {
		
		mTvTitle = (TextView) findViewById(R.id.tvTitle);
		mTvTitle.setText(mTitle);

		mBtnLeft = (Button) findViewById(R.id.btnLeft);
		mBtnLeft.setText(mBtn);

		setStringListView();
	}


	public void setList(ArrayList<ListItem> arryList, OnItemClickListener onItemClickListener){
		mArryList = arryList;
		mListItemClickListener = onItemClickListener;
	}

	private void setStringListView(){

		mListView = (ListView)findViewById(R.id.lvCustomPopup);
		mAdapter = new Custom_ListAdapter(mContext, R.layout.comm_list_item_txt_nor_chk, mArryList);
		mListView.setDivider(null);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(mListItemClickListener);
	}
}
