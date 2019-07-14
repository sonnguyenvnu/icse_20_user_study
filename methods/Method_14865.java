protected void setQRCode(User user){
  try {
    qRCodeBitmap=EncodingHandler.createQRCode(HttpRequest.URL_GET + JSON.toJSONString(new JSONRequest(new apijson.demo.server.model.User(userId))),(int)(2 * getResources().getDimension(R.dimen.qrcode_size)));
  }
 catch (  WriterException e) {
    e.printStackTrace();
    Log.e(TAG,"initData  try {Bitmap qrcode = EncodingHandler.createQRCode(contactJson, ivQRCodeCode.getWidth());" + " >> } catch (WriterException e) {" + e.getMessage());
  }
  runUiThread(new Runnable(){
    @Override public void run(){
      ivQRCodeProgress.setVisibility(View.GONE);
      ivQRCodeCode.setImageBitmap(qRCodeBitmap);
    }
  }
);
}
