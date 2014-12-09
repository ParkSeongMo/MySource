package com.example.rearrangehorizontalscrollview;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This customized Horizontal ScrollView is possible to rearrange item
 * 
 * @author parksmo
 *
 */
public class RearrangeHorizontalScrollView extends HorizontalScrollView {

	private final String TAG = "RearrangeHorizontalScrollView";
	private final int AUTO_SCROLL_TIME = 200;
	
	private Context mContext;
	
	private LinearLayout mMainChildView;
	private View[] mChildView;	
	private View mDragView;

	private GestureDetector mGestureDetector;
	
	private int mPreIndex = 0;
	private boolean mIsDrop = true;
	private float mLongPressScale = 1.3F;
	
	private ArrayList<String> mData;
	
	public RearrangeHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		mContext = context;
		initView();		
	}
	
	public RearrangeHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub

		mContext = context;
		initView();		
	}
	
	private void initView(){
		
		mGestureDetector = new GestureDetector(mContext, new MyGestureLisnener());		
		initChildView();
	}
	
	private void initChildView(){

		mMainChildView = new LinearLayout(mContext);		
		addView(mMainChildView);		
	}
	
	private void setChildView(){
		
		if(mData!=null && mData.size()>0){
			
			LayoutInflater inflator = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			mChildView = new View[mData.size()];
			
			for(int i=0; i<mData.size(); i++){
				
				mChildView[i] = inflator.inflate(R.layout.layout_scrollview_item, null);
				mChildView[i].setOnClickListener(mOnClickListener);
				mChildView[i].setOnTouchListener(mOnTouchListener);				
				mChildView[i].setOnDragListener(mOnDragListener);
				mMainChildView.addView(mChildView[i]);
				
				setListItem(mChildView[i], i);
			}
		}
	}
	
	private void setListItem(View view, int index){
		
		TextView tv = (TextView)view.findViewById(R.id.textView1);
		tv.setText(String.valueOf(index));		
	}
	
	public void setItem(ArrayList<String> data){
		
		mData = data;

		setChildView();
	}
	
	private OnClickListener mOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d(TAG, "[RearrangeHorizontalScrollView] onClick");
		}
	};
	
	private OnTouchListener mOnTouchListener= new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			
			mDragView = v;		

			Log.d(TAG, "[RearrangeHorizontalScrollView] onTouch action:"+event.getAction());
			
			mGestureDetector.onTouchEvent(event);

			return false;			
		}
	};
	

	private OnDragListener mOnDragListener = new OnDragListener() {
		
		@Override
		public boolean onDrag(View v, DragEvent event) {
			// TODO Auto-generated method stub

			switch (event.getAction()) {
			
			case DragEvent.ACTION_DRAG_STARTED:
				break;
				
			case DragEvent.ACTION_DRAG_ENTERED:
				
				int viewWidth = v.getMeasuredWidth();

				mIsDrop = false;
				
				for (int i = 0, j = mMainChildView.getChildCount(); i < j; i++) {
					
					if (mMainChildView.getChildAt(i) == v) {						

						int x = (int) v.getX();
						int scrollX = getScrollX();
						
						if(i>mPreIndex){	// moving right
							if(j-1==i){	//	last
								if(!mIsDrop) smoothScrollTo(viewWidth*j, 0);	//	scroll to end
							}else{

								int itemWidth = viewWidth*(i+1);
								int scrollWidth = getMeasuredWidth();
								
								if(itemWidth>=scrollWidth+getScrollX()){

									smoothScrollTo((itemWidth-scrollWidth), 0);	
									setAutoDelayScrollX(getScrollX()+viewWidth);
								}
							}
								
						}else if(i<mPreIndex){	//	moving left
							
							if(scrollX != 0 
									&&x <= scrollX 
									&&x > 0){
								
								int scroll = scrollX%x;
								
								smoothScrollTo(scrollX-scroll, 0);
								
								if(scrollX-scroll>1) setAutoDelayScrollX(getScrollX()-viewWidth);								
							}
						}
					}
				}
				v.setAlpha(0.5F); 
				break;
				
			case DragEvent.ACTION_DRAG_EXITED:
				
				for (int i = 0, j = mMainChildView.getChildCount(); i < j; i++) {
					if (mMainChildView.getChildAt(i) == v) mPreIndex = i;
					
				}
				v.setAlpha(1F);
				break;
				
			case DragEvent.ACTION_DROP:
				
				mIsDrop = true;
				View view = (View) event.getLocalState();
				view.setBackgroundResource(R.xml.backgound_selector);
				for (int i = 0, j = mMainChildView.getChildCount(); i < j; i++) {
					if (mMainChildView.getChildAt(i) == v) {
						mMainChildView.removeView(view);
						mMainChildView.addView(view, i);
						break;
					}
				}
				break;
				
			case DragEvent.ACTION_DRAG_ENDED:	
				
				v.setAlpha(1F);
				break;
				
			case DragEvent.ACTION_DRAG_LOCATION:					
				break;
			default:
				break;
			}
		
			return true;
		}
	};
	
	private void setAutoDelayScrollX(final int scrollX){
		
		postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(!mIsDrop) smoothScrollTo(scrollX, 0);
			}
		}, AUTO_SCROLL_TIME);
	}
	
	public float getLongPressScale() {
		return mLongPressScale;
	}

	public void setLongPressScale(float longPressScale) {
		this.mLongPressScale = longPressScale;
	}

	private class MyGestureLisnener extends SimpleOnGestureListener{

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			return super.onSingleTapConfirmed(e);
		}
		
		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onLongPress(e);
			ClipData data = ClipData.newPlainText("", "");
			MyDragShadowBuilder shadowBuilder = new MyDragShadowBuilder(mDragView);
			mDragView.startDrag(data, shadowBuilder, mDragView, 0);			
		}
		
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return true;	//	long press
		}
	}
	
	private class MyDragShadowBuilder extends View.DragShadowBuilder{
	
		private final WeakReference<View> mView;
		
		public MyDragShadowBuilder(View view){
			super(view);
			
			mView = new WeakReference<View>(view);
		}
		
		@Override
		public void onDrawShadow(Canvas canvas) {
			// TODO Auto-generated method stub
			canvas.scale(mLongPressScale,  mLongPressScale);
			super.onDrawShadow(canvas);
		}
		
		@Override
		public void onProvideShadowMetrics(Point shadowSize,
				Point shadowTouchPoint) {
			// TODO Auto-generated method stub
		
			View view = mView.get();
			view.setBackgroundColor(Color.RED);
			if(view!=null){
				shadowSize.set((int)(view.getWidth()*mLongPressScale), (int)(view.getHeight()*mLongPressScale));
				shadowTouchPoint.set(shadowSize.x/2, shadowSize.y/2);
			}
		}
	}	
}
