package com.example.custom;

import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

/**
 * @author parksmo
 *
 */
public class CuzRadioBtnManager {

	private ArrayList<CuzRadioBtn> arryList;

	public CuzRadioBtnManager(){

		arryList =  new ArrayList<CuzRadioBtn>();
	}

	public void AddCuzRatioBtn(CuzRadioBtn btn){

		arryList.add(btn);

		btn.getRadioButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				setUnChecked((RadioButton)v);
			}
		});

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				CuzRadioBtn button = (CuzRadioBtn)v;
				RadioButton radioButton = button.getRadioButton();
				radioButton.setChecked(true);
				setUnChecked(button.getRadioButton());
			}
		});
	}

	private void setUnChecked(RadioButton checkedBtn){

		for(CuzRadioBtn btn : arryList){
			RadioButton radioButton = btn.getRadioButton();		
			if(radioButton!=checkedBtn)
				radioButton.setChecked(false);		
		}
	}
}
