package com.example.demobarcode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.barcode.BarcodeCreater;
import com.google.zxing.BarcodeFormat;

/**
 * @author Administrator
 * 서버에서 HTML을 가져와 웹뷰에 적용시키는 소스
 */
public class ActivityBarcode extends Activity {

	private ImageView iv;
	private ImageView iv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barcode);

		setControl();

		// barcode data
		String barcode_data = "12345678901234567890";

		// barcode image
		Bitmap bitmap = null;


//			bitmap = new BarcodeCreater().encodeAsBitmap(barcode_data, BarcodeFormat.CODE_128, iv.getWidth(), iv.getHeight());
//			iv.setImageBitmap(bitmap);
//			
//			bitmap = new BarcodeCreater().encodeAsBitmap(barcode_data, BarcodeFormat.CODE_128, 160, 80);
//			iv2.setImageBitmap(bitmap);
			
		BarcodeCreater barcodeCreater = new BarcodeCreater();
		barcodeCreater.encodeAsBitmap(barcode_data, BarcodeFormat.CODE_128, 160, 80, iv);
		barcodeCreater.encodeAsBitmap("http://www.jw.org", BarcodeFormat.QR_CODE, 200, 200, iv2);
		

	}

	

	/**
	 * 
	 */
	private void setControl(){


		iv = (ImageView)findViewById(R.id.imageView1);
		iv2 = (ImageView)findViewById(R.id.imageView2);
	}

}

