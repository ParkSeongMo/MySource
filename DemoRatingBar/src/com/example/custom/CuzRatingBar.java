package com.example.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.demoratingbar.R;

/**
 * @author parksmo
 *
 */
public class CuzRatingBar extends View {
	
	public static final int RATING_MODE_NORMAL = 1;
	public static final int RATING_MODE_HALF= 2;
		
	private float starWidth;			//	a star width
	private float starHeight;			//	a star Height
	private float starSpacing;			//	between star and star
	private float starRating;			//	star rating
	private float starMaxNum;		//	max star number
	private float starNum;				//	star number
	private float starRanaeRate;		//	star range rate
	private boolean isTouchable;	//	it is possible to touch star
		
	private Bitmap starBmp;			//	star image
	private Bitmap starBg;				//	star backgound image

	private int ratingMode = RATING_MODE_NORMAL;
	
	/**
	 * @author parksmo
	 *	A callback that notifies clients when the star rating has been changed
	 */
	public interface OnRatingBarChangeListener{
		/**
		 * 	Notification that the star rating has changed
		 * @param myRatingBar : RatingBar
		 * @param rating : rating number
		 */
		void OnRatingChanged(CuzRatingBar myRatingBar, float rating);
		/**
		 * Notification that the star rating is changing
		 * @param myRatingBar
		 * @param rating
		 */
		void OnRatingChanging(CuzRatingBar myRatingBar, float rating);
	}
	
	private OnRatingBarChangeListener onRatingBarChangeListener;
	
	public CuzRatingBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initRatingBar(context, attrs);
	}
	
	private void initRatingBar(Context context, AttributeSet attrs){
		
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CuzRatingBar);
		
		this.isTouchable = typedArray.getBoolean(R.styleable.CuzRatingBar_star_touchable, true);
		this.starWidth = typedArray.getDimension(R.styleable.CuzRatingBar_star_width, 0);
		this.starHeight = typedArray.getDimension(R.styleable.CuzRatingBar_star_height, 0);
		this.starSpacing = typedArray.getDimension(R.styleable.CuzRatingBar_star_spacing, 0);
		this.starNum = typedArray.getInt(R.styleable.CuzRatingBar_star_num, 5);
		this.starMaxNum = typedArray.getInt(R.styleable.CuzRatingBar_star_range, 5);
		this.starRating = typedArray.getFloat(R.styleable.CuzRatingBar_star_rating, 5);
		this.starBmp = getStarBitmap(typedArray.getDrawable(R.styleable.CuzRatingBar_star_img), R.drawable.frm_profile_list_icon_heart_on);
		this.starBg = getStarBitmap(typedArray.getDrawable(R.styleable.CuzRatingBar_star_bg), R.drawable.frm_profile_list_icon_heart_off);		
		
		setStarRangeRate();
		starRating = starRating/starRanaeRate;
		
		typedArray.recycle();		
	}
	
	private Bitmap getStarBitmap(Drawable drawable, int DefaultDrawable){
		
		Bitmap retBmp = getOriginalBitmap(drawable, DefaultDrawable);
				
		if(starWidth!=0||starHeight!=0) retBmp = getResizedBitmap(retBmp);			
		
		return retBmp;
	}	
	
	private Bitmap getOriginalBitmap(Drawable drawable, int DefaultDrawable){
		
		Bitmap retBmp = null;
		
		if(drawable==null) retBmp = BitmapFactory.decodeResource(getResources(), DefaultDrawable);
		else retBmp = ((BitmapDrawable)drawable).getBitmap();
		
		return retBmp;
	}	

	private Bitmap getResizedBitmap(Bitmap orgBmp){
		Bitmap retBmp = null;
		
		if(orgBmp != null){

			float height = getResizedBitmapLength(orgBmp.getHeight(), starHeight);		
			float width = getResizedBitmapLength(orgBmp.getWidth(), starWidth);
			
			retBmp = Bitmap.createScaledBitmap(orgBmp, (int)width, (int)height, true);
		}
		
		return retBmp;
	}
	
	private float getResizedBitmapLength(float bitmapSize, float changeSize){
			
		float retSize = changeSize;
		
		if(bitmapSize>changeSize){
			
			float percent = (float)(bitmapSize/100);
			float scale = (float)(changeSize/percent);
			
			retSize *= (scale/100);
		}
		
		return retSize;
	}	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		for(int i=0; i<starNum; i++) drawStar(canvas, i);
	}

	private void drawStar(Canvas canvas, int position){
		
		float fraction = starRating - position;
		
		if((position+1) <= starRating) canvas.drawBitmap(starBmp, position*(starBmp.getWidth()+starSpacing), 0, null);
		else{
			
			if(fraction>0 && fraction<1 && ratingMode == RATING_MODE_HALF){
			
				
				if(fraction>=0.5) fraction = 0.5f;
				else fraction = 0;
				
				int sourceW = starBmp.getWidth();
				int sourceH = starBmp.getHeight();
				int targetW = (int)(starBmp.getWidth()*fraction);
				int bgW = sourceW - targetW;
				
				if(targetW>0){
					Bitmap croppedBmp = Bitmap.createBitmap(starBmp, 0, 0, targetW, sourceH);
					canvas.drawBitmap(croppedBmp, position*(sourceW+starSpacing), 0, null);
				}
				
				if(bgW>0){
					Bitmap croppedBg = Bitmap.createBitmap(starBg, targetW, 0, bgW, sourceH);
					canvas.drawBitmap(croppedBg, position*(sourceW+starSpacing)+targetW, 0 , null);
				}
				
			}else canvas.drawBitmap(starBg, position*(starBg.getWidth()+starSpacing), 0, null);		
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		if(starBg!=null){
			final int width = (int) ((starBg.getWidth()+starSpacing)*starNum);
			final int height = starBg.getHeight();
			setMeasuredDimension(resolveSizeAndState(width, widthMeasureSpec, 0), 
					resolveSizeAndState(height, heightMeasureSpec, 0));
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(!isTouchable) return false;
		float position = 0;
		int action = event.getAction();
		switch(action){
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			position = getRelativePosition(event.getX());
			if(position!=starRating) {
				setRating(position);
				if(onRatingBarChangeListener!=null) onRatingBarChangeListener.OnRatingChanging(this, starRating*starRanaeRate);
			}
			break;
		case MotionEvent.ACTION_UP:
			position = getRelativePosition(event.getX());
			if(position!=starRating) setRating(position);
			if(onRatingBarChangeListener!=null) onRatingBarChangeListener.OnRatingChanged(this, starRating*starRanaeRate);
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		}
		return true;
	}
	
	private float getRelativePosition(float x){
		
		float position = x/(starBg.getWidth()+starSpacing);
		position = Math.max(position, 0);
		position = Math.min(position, starNum);
					
		if(ratingMode == RATING_MODE_HALF){
			
			float floor = (float) Math.floor(position);
			float temp = position%floor;
			
			if(position<1) temp = position;
			
			if(temp>0 && temp<0.5) position = floor+0.5f;
			else if(temp>=0.5) position = floor+1.0f;
			else position = floor;
			
		}else {
			position = (float) Math.ceil(position);	
		}	
		
		return position;
	}
	
	private void setRating(float rating){
		
		starRating = rating;		
		invalidate();
	}
	
	private void setStarRangeRate(){

		starRanaeRate = starMaxNum/starNum;
	}
	
	//	------- Get & Set -------//
	
	public void setStarRating(float rating){
		
		this.starRating = rating/starRanaeRate;
		invalidate();
	}
	
	public float getStarRating(){
		
		return this.starRating*starRanaeRate;
	}
	
	public void setTouchable(boolean isTouchable){
		
		this.isTouchable = isTouchable;
	}
	
	public boolean isTouchable(){
		
		return this.isTouchable;
	}
		
	public void setOnRatingBarChangeListener(OnRatingBarChangeListener listener){
		
		onRatingBarChangeListener = listener;
	}
	
	public OnRatingBarChangeListener getOnRatingBarChangeListener(){
		
		return this.onRatingBarChangeListener;
	}

	public int getRatingMode() {
		
		return ratingMode;
	}

	public void setRatingMode(int ratingMode) {
		
		this.ratingMode = ratingMode;
	}

	public float getMaxStarNum() {
		
		return starMaxNum;
	}

	public float getStarNum() {
		
		return starNum;
	}
	
	public void setStarRange(float starMaxNum, float starNum) {
		
		this.starMaxNum = starMaxNum;
		this.starNum = starNum;
		setStarRangeRate();
	}
	
}
