package com.example.demoradiobtn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.custom.CuzRadioBtn;
import com.example.custom.CuzRadioBtnManager;

/**
 * @author parksmo
 */
public class ActivityCuzRadioBtn extends Activity implements OnClickListener {
	
	private CuzRadioBtn cuzRadioBtn1;
	private CuzRadioBtn cuzRadioBtn2;
	
	private CuzRadioBtn cuzRadioBtn11;
	private CuzRadioBtn cuzRadioBtn12;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cuz_radio_btn);

		setControl();
	}

	
	/**
	 * 
	 */
	private void setControl(){

		setRatioButton();
	}
	
	private void setRatioButton(){
		
	
		cuzRadioBtn1 = (CuzRadioBtn)findViewById(R.id.CuzRadioBtn1);
		cuzRadioBtn1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub

			}
		});
		
		cuzRadioBtn2 = (CuzRadioBtn)findViewById(R.id.CuzRadioBtn2);
		cuzRadioBtn2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub

			}
		});
		
		
		CuzRadioBtnManager mgr1 = new CuzRadioBtnManager();
		mgr1.AddCuzRatioBtn(cuzRadioBtn1);
		mgr1.AddCuzRatioBtn(cuzRadioBtn2);
		
		
		cuzRadioBtn11 = (CuzRadioBtn)findViewById(R.id.CuzRadioBtn11);
		cuzRadioBtn11.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub

			}
		});
		
		cuzRadioBtn12 = (CuzRadioBtn)findViewById(R.id.CuzRadioBtn12);
		cuzRadioBtn12.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub

			}
		});
		
		
		CuzRadioBtnManager mgr2 = new CuzRadioBtnManager();
		mgr2.AddCuzRatioBtn(cuzRadioBtn11);
		mgr2.AddCuzRatioBtn(cuzRadioBtn12);

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId()==R.id.radio1){
			RadioButton radioButton = (RadioButton)v;
			Log.d("parskmo", "onClick checked : " + radioButton.isChecked());
		}
	}
	

}

