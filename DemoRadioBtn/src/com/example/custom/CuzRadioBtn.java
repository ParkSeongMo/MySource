package com.example.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.demoradiobtn.R;

/**
 * @author parksmo
 *
 */
public class CuzRadioBtn extends LinearLayout {

	private RadioButton radioBtn;
	private TextView tv;
	private OnCheckedChangeListener onClickListener;

	public CuzRadioBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		initView(context, attrs);
	}

	private void initView(Context context, AttributeSet attrs) {

		TypedArray typedArrayAttr = context.obtainStyledAttributes(attrs, R.styleable.CuzRadioBtn);
		View view = getView(context, typedArrayAttr.getResourceId(R.styleable.CuzRadioBtn_LayoutId, -1));
		initControl(view, typedArrayAttr);
	}

	private View getView(Context context, int viewId){

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		return inflater.inflate(viewId, this, true);
	}

	private void initControl(View view, TypedArray typedArrayAttr){		

		initRadioButton(view, typedArrayAttr);
		initTextView(view, typedArrayAttr);
	}

	private void initRadioButton(View view, TypedArray typedArrayAttr){

		radioBtn = (RadioButton)view.findViewById(R.id.radio1);

		if(onClickListener!=null) radioBtn.setOnCheckedChangeListener(onClickListener);

		Drawable drawable = typedArrayAttr.getDrawable(R.styleable.CuzRadioBtn_BackgroundImage);
		if(drawable!=null) radioBtn.setBackgroundDrawable(drawable);

		float width = typedArrayAttr.getDimension(R.styleable.CuzRadioBtn_IconWidth, -1);
		if(width!=-1) radioBtn.setWidth((int) width);

		float height = typedArrayAttr.getDimension(R.styleable.CuzRadioBtn_IconHeight, -1);
		if(height!=-1) radioBtn.setHeight((int) height);
				
		float marginLeft = typedArrayAttr.getDimension(R.styleable.CuzRadioBtn_BtnMarginLeft, -1);		
		if(marginLeft!=-1){
			
			LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.setMargins((int)marginLeft, 0, 0, 0);
			radioBtn.setLayoutParams(lp);
		}		
	}

	private void initTextView(View view, TypedArray typedArrayAttr){

		tv = (TextView)findViewById(R.id.textView1);

		String contents = typedArrayAttr.getString(R.styleable.CuzRadioBtn_Text);
		if(!TextUtils.isEmpty(contents)) tv.setText(contents);

		float textSize = typedArrayAttr.getDimension(R.styleable.CuzRadioBtn_TextSize, -1);
		if(textSize!=-1) tv.setTextSize(textSize);

		String textColor = typedArrayAttr.getString(R.styleable.CuzRadioBtn_TextColor);
		if(!TextUtils.isEmpty(textColor)) tv.setTextColor(Color.parseColor(textColor));		

		float marginLeft = typedArrayAttr.getDimension(R.styleable.CuzRadioBtn_TextMarginLeft, -1);		
		if(marginLeft!=-1){
			
			LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.setMargins((int)marginLeft, 0, 0, 0);
			tv.setLayoutParams(lp);
		}			
	}

	public RadioButton getRadioButton(){

		return radioBtn;
	}

	public void setOnCheckedChangeListener(OnCheckedChangeListener clickListener){

		this.onClickListener = clickListener;
		radioBtn.setOnCheckedChangeListener(onClickListener);
	}

	public void setText(String text){

		if(tv!=null) tv.setText(text);
	}

	public boolean isChecked(){

		return radioBtn.isChecked();
	}
}

