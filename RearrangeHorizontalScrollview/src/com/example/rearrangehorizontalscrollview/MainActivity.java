package com.example.rearrangehorizontalscrollview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	
	RearrangeHorizontalScrollView scrollView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		ArrayList<String> data = new ArrayList<String>();
		for(int i=0; i<15; i++)
			data.add(String.valueOf(i));
		
		scrollView  = (RearrangeHorizontalScrollView)findViewById(R.id.scrollView);
		
		scrollView.setItem(data);
	}
}
