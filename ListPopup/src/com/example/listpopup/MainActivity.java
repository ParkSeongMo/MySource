package com.example.listpopup;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	private final String TAG = "MainActivty";

	private Button mBtnPopup;
	private Button mBtnCheck;
	private ListView mListView;

	private Custom_ListAdapter mAdapter;
	private ArrayList<ListItem> mArryListItem;

	private ListDialog mListDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setControl();
		setList();
	}

	private void setControl(){

		mBtnPopup = (Button)findViewById(R.id.btnPopup);
		mBtnCheck= (Button)findViewById(R.id.btnCheck);
		
		mBtnPopup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				setListDialog();
			}
		});
		
		mBtnCheck.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mAdapter.setVisibleCheckBox(!mAdapter.isVisibleCheckBox());
			}
		});
	}

	private void setListData(){

		mArryListItem = new ArrayList<ListItem>();

		for(int i=0; i<20; i++){
			ListItem item = new ListItem(String.format("item %d", i), false);
			mArryListItem.add(item);
		}
	}
	private void setList(){

		setListData();

		mListView = (ListView)findViewById(R.id.listView1);
		mAdapter = new Custom_ListAdapter(MainActivity.this, R.layout.comm_list_item_txt_nor_chk, mArryListItem);
		mListView.setDivider(null);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
			}
		});		
	}

	private void setListDialog(){

		if(mListDialog==null){
			mListDialog = new ListDialog(MainActivity.this, "Select Item", new OnClickListener() {
	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mListDialog.hide();
					mListDialog = null;
				}
			});
			mListDialog.setList(mArryListItem, new OnItemClickListener() {
	
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					Log.d(TAG, String.format("onItemClick() position:%d", position)); 
				}
			});
		}
		mListDialog.setBtnString("Confirm");
		mListDialog.show();
	}
	
	public void onBackPressed() {
		
		if(mListDialog.isShowing()){
			mListDialog.hide();
			mListDialog = null;
		}else finish();
	};
}
