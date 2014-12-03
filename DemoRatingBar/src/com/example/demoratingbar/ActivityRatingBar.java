package com.example.demoratingbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.custom.CuzRatingBar;
import com.example.custom.CuzRatingBar.OnRatingBarChangeListener;

public class ActivityRatingBar extends Activity {

	private CuzRatingBar myRatingBarHalf;
	private CuzRatingBar myRatingBarNor;
	private CuzRatingBar myRatingBar1;
	private CuzRatingBar myRatingBar2;
	private CuzRatingBar myRatingBar3;
	
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private TextView tv5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cuzratingbar);
		
		setControl();
	}
	
	private void setControl(){

		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		tv3 = (TextView)findViewById(R.id.textView3);
		tv4 = (TextView)findViewById(R.id.textView4);
		tv5 = (TextView)findViewById(R.id.textView5);
		
		tv1.setText(String.format("heart:%.1f", 3.5f));
		tv2.setText(String.format("heart:%.1f", 3f));
		tv3.setText(String.format("heart:%.1f", 1.5f));
		tv4.setText(String.format("heart:%.1f", 8.1f));
		tv5.setText(String.format("heart:%.1f", 8.8f));
		
		myRatingBarHalf = (CuzRatingBar)findViewById(R.id.myRatingBar1);
		myRatingBarHalf.setRatingMode(CuzRatingBar.RATING_MODE_HALF);
		myRatingBarHalf.setStarRating(3.5f);
		myRatingBarHalf.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {			
			@Override
			public void OnRatingChanged(CuzRatingBar myRatingBar, float rating) {
				// TODO Auto-generated method stub
				tv1.setText(String.format("heart:%.1f", rating));
			}

			@Override
			public void OnRatingChanging(CuzRatingBar myRatingBar, float rating) {
				// TODO Auto-generated method stub
				tv1.setText(String.format("heart:%.1f", rating));
			}
		});
		
		myRatingBarNor = (CuzRatingBar)findViewById(R.id.myRatingBar2);
		myRatingBarNor.setStarRating(3f);
		myRatingBarNor.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {			
			@Override
			public void OnRatingChanged(CuzRatingBar myRatingBar, float rating) {
				// TODO Auto-generated method stub
				tv2.setText(String.format("heart:%.1f", rating));
			}

			@Override
			public void OnRatingChanging(CuzRatingBar myRatingBar, float rating) {
				// TODO Auto-generated method stub				
				tv2.setText(String.format("heart:%.1f", rating));
			}
		});
		
		myRatingBar1 = (CuzRatingBar)findViewById(R.id.myRatingBar3);
		myRatingBar1.setStarRange(3, 6);
		myRatingBar1.setStarRating(1.5f);
		myRatingBar1.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {			
			@Override
			public void OnRatingChanged(CuzRatingBar myRatingBar, float rating) {
				// TODO Auto-generated method stub
				tv3.setText(String.format("heart:%.1f", rating));
			}

			@Override
			public void OnRatingChanging(CuzRatingBar myRatingBar, float rating) {
				// TODO Auto-generated method stub
				tv3.setText(String.format("heart:%.1f", rating));
			}
		});
		
		myRatingBar2 = (CuzRatingBar)findViewById(R.id.myRatingBar4);
		myRatingBar2.setStarRange(10, 5);
		myRatingBar2.setStarRating(8.1f);
		myRatingBar2.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {			
			@Override
			public void OnRatingChanged(CuzRatingBar myRatingBar, float rating) {
				// TODO Auto-generated method stub
				tv4.setText(String.format("heart:%.1f", rating));
			}

			@Override
			public void OnRatingChanging(CuzRatingBar myRatingBar, float rating) {
				// TODO Auto-generated method stub
				tv4.setText(String.format("heart:%.1f", rating));
			}
		});
		
		myRatingBar3 = (CuzRatingBar)findViewById(R.id.myRatingBar5);
		myRatingBar3.setRatingMode(CuzRatingBar.RATING_MODE_HALF);
		myRatingBar3.setStarRange(10, 5);
		myRatingBar3.setStarRating(8.8f);
		myRatingBar3.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {			
			@Override
			public void OnRatingChanged(CuzRatingBar myRatingBar, float rating) {
				// TODO Auto-generated method stub
				tv5.setText(String.format("heart:%.1f", rating));
			}

			@Override
			public void OnRatingChanging(CuzRatingBar myRatingBar, float rating) {
				// TODO Auto-generated method stub
				tv5.setText(String.format("heart:%.1f", rating));
			}
		});
		
	}
}
