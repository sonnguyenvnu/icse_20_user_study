public static Bitmap getCroppedBitmap(Bitmap srcBmp){
  Bitmap dstBmp;
  if (srcBmp.getWidth() >= srcBmp.getHeight()) {
    dstBmp=Bitmap.createBitmap(srcBmp,srcBmp.getWidth() / 2 - srcBmp.getHeight() / 2,0,srcBmp.getHeight(),srcBmp.getHeight());
  }
 else {
    dstBmp=Bitmap.createBitmap(srcBmp,0,srcBmp.getHeight() / 2 - srcBmp.getWidth() / 2,srcBmp.getWidth(),srcBmp.getWidth());
  }
  return dstBmp;
}
