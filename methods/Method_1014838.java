private void init(){
  mCurrentMode=IDLE_MODE;
  isDrawBorder=false;
  isHorizontal=false;
  mBorderPaint=new Paint();
  mBorderPaint.setColor(Color.WHITE);
  mBorderPaint.setStyle(Paint.Style.STROKE);
  mBorderPaint.setAntiAlias(true);
  mBorderPaint.setStrokeWidth(4);
  mDeleteBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.btn_dialog_del_normal);
  mScaleBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.btn_dialog_scale_normal);
  mHorizonBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.btn_dialog_horizontal_normal);
  mVerticalBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.btn_dialog_vertical_normal);
  mFlipBitmap=mHorizonBitmap;
  isHorizontal=true;
}
