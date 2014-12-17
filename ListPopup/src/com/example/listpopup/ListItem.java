package com.example.listpopup;

public class ListItem {

	private String mText;
	private boolean mIsChecked;
	
	public ListItem(String text, boolean isChecked){

		this.mText = text;
		this.mIsChecked = isChecked;
	}
	
	public String getText() {
		return mText;
	}
	
	public void setText(String text) {
		this.mText = text;
	}
	
	public boolean isChecked() {
		return mIsChecked;
	}
	
	public void setChecked(boolean isChecked) {
		this.mIsChecked = isChecked;
	}
	
	
}
