package com.example.barcode;

import java.util.EnumMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class BarcodeCreater {
	
	private final int WHITE = 0xFFFFFFFF;
	private final int BLACK = 0xFF000000;

	public void encodeAsBitmap(String contents, BarcodeFormat format, int imgWidth, int imgHight, ImageView iv){		
		 
		new BarcodeThread().execute(new BarcodeInfo(contents, format, imgWidth, imgHight, iv));
	}
	
	
	public Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int imgWidth, int imgHight){
				
		if (contents == null) return null;
		
		Map<EncodeHintType, Object> hints = getEncodeHintType(contents);
		
		MultiFormatWriter writer = new MultiFormatWriter();
		BitMatrix result;
		
		try {
			result = writer.encode(contents, format, imgWidth, imgHight, hints);
		} catch (IllegalArgumentException iae) {			
			// Unsupported format
			return null;			
		} catch (WriterException iae) {			
			// Unsupported format
			return null;
		}
		
		int width = result.getWidth();
		int height = result.getHeight();
		int[] pixels = makeBarcodePixels(result, width, height);

		Bitmap retBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		retBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		
		return retBitmap;
	}
	
	private Map<EncodeHintType, Object> getEncodeHintType(String contents){
		
		Map<EncodeHintType, Object> retHints = null;
		
		String encoding = guessAppropriateEncoding(contents);
		
		if (encoding != null) {
			retHints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			retHints.put(EncodeHintType.CHARACTER_SET, encoding);
		}
		
		return retHints;
	}
	
	private String guessAppropriateEncoding(CharSequence contents) {
		
		for (int i = 0; i < contents.length(); i++) 
			if (contents.charAt(i) > 0xFF) return "UTF-8";			
		
		return null;
	}
	
	private int[] makeBarcodePixels(BitMatrix result, int width, int height){
		
		int[] retPixels = new int[width * height];
		
		for (int y = 0; y < height; y++) {
			
			int offset = y * width;
			for (int x = 0; x < width; x++) 
				retPixels[offset + x] = result.get(x, y) ? BLACK : WHITE;			
		}
		
		return retPixels;
	}
	
	private class BarcodeThread extends AsyncTask<BarcodeInfo, Void, Bitmap>{
		
		private BarcodeInfo barcodeInfo;
		
		@Override
		protected Bitmap doInBackground(BarcodeInfo... params) {
			// TODO Auto-generated method stub
			
			barcodeInfo = params[0];			
			
			return encodeAsBitmap(barcodeInfo.getContents(),barcodeInfo.getFormat(), barcodeInfo.getImgWidth(), barcodeInfo.getImgHeight());
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(barcodeInfo!=null 
					&& barcodeInfo.getIv() != null 
					&& result != null)
				barcodeInfo.getIv().setImageBitmap(result);
		}
	}	
	
	private class BarcodeInfo{
		
		private String contents;
		private BarcodeFormat format;
		private int imgWidth;
		private int imgHight;
		private ImageView iv;
		
		public BarcodeInfo(String contents, BarcodeFormat format, int width, int height, ImageView iv){

			setContents(contents);
			setFormat(format);
			setImgWidth(width);
			setImgHeight(height);
			setIv(iv);
		}
		
		public BarcodeFormat getFormat() {
			return format;
		}

		public void setFormat(BarcodeFormat format) {
			this.format = format;
		}

		public int getImgWidth() {
			return imgWidth;
		}

		public void setImgWidth(int imgWidth) {
			this.imgWidth = imgWidth;
		}

		public int getImgHeight() {
			return imgHight;
		}

		public void setImgHeight(int imgHight) {
			this.imgHight = imgHight;
		}

		public ImageView getIv() {
			return iv;
		}

		public void setIv(ImageView iv) {
			this.iv = iv;
		}

		public String getContents() {
			return contents;
		}

		public void setContents(String contents) {
			this.contents = contents;
		}
	}

}
