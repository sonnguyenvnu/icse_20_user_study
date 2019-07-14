private Bitmap loadBitmap(Uri imageFileUri){
  Display currentDisplay=getWindowManager().getDefaultDisplay();
  float dw=currentDisplay.getWidth();
  float dh=currentDisplay.getHeight();
  Bitmap returnBmp=Bitmap.createBitmap((int)dw,(int)dh,Bitmap.Config.ARGB_4444);
  try {
    BitmapFactory.Options bmpFactoryOptions=new BitmapFactory.Options();
    bmpFactoryOptions.inJustDecodeBounds=true;
    returnBmp=BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri),null,bmpFactoryOptions);
    int heightRatio=(int)Math.ceil(bmpFactoryOptions.outHeight / dh);
    int widthRatio=(int)Math.ceil(bmpFactoryOptions.outWidth / dw);
    Log.v("HEIGHTRATIO","" + heightRatio);
    Log.v("WIDTHRATIO","" + widthRatio);
    if (heightRatio > 1 && widthRatio > 1) {
      if (heightRatio > widthRatio) {
        bmpFactoryOptions.inSampleSize=heightRatio;
      }
 else {
        bmpFactoryOptions.inSampleSize=widthRatio;
      }
    }
    bmpFactoryOptions.inJustDecodeBounds=false;
    returnBmp=BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri),null,bmpFactoryOptions);
  }
 catch (  FileNotFoundException e) {
    Log.v("ERROR",e.toString());
  }
  return returnBmp;
}
