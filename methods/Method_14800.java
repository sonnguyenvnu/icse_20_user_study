/** 
 * ?????
 */
protected void setQRCode(){
  runThread(TAG + "setQRCode",new Runnable(){
    @Override public void run(){
      try {
        qRCodeBitmap=EncodingHandler.createQRCode(Constant.APP_DOWNLOAD_WEBSITE,(int)(2 * getResources().getDimension(R.dimen.qrcode_size)));
      }
 catch (      WriterException e) {
        e.printStackTrace();
        Log.e(TAG,"initData  try {Bitmap qrcode = EncodingHandler.createQRCode(contactJson, ivContactQRCodeCode.getWidth());" + " >> } catch (WriterException e) {" + e.getMessage());
      }
      runUiThread(new Runnable(){
        @Override public void run(){
          ivAboutQRCode.setImageBitmap(qRCodeBitmap);
        }
      }
);
    }
  }
);
}
