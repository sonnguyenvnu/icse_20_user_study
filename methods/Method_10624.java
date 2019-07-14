public static Bitmap creatQRCode(CharSequence content,int QR_WIDTH,int QR_HEIGHT,int backgroundColor,int codeColor){
  Bitmap bitmap=null;
  try {
    if (content == null || "".equals(content) || content.length() < 1) {
      return null;
    }
    Hashtable<EncodeHintType,String> hints=new Hashtable<EncodeHintType,String>();
    hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
    BitMatrix bitMatrix=new QRCodeWriter().encode(content + "",BarcodeFormat.QR_CODE,QR_WIDTH,QR_HEIGHT,hints);
    int[] pixels=new int[QR_WIDTH * QR_HEIGHT];
    for (int y=0; y < QR_HEIGHT; y++) {
      for (int x=0; x < QR_WIDTH; x++) {
        if (bitMatrix.get(x,y)) {
          pixels[y * QR_WIDTH + x]=codeColor;
        }
 else {
          pixels[y * QR_WIDTH + x]=backgroundColor;
        }
      }
    }
    bitmap=Bitmap.createBitmap(QR_WIDTH,QR_HEIGHT,Bitmap.Config.ARGB_8888);
    bitmap.setPixels(pixels,0,QR_WIDTH,0,0,QR_WIDTH,QR_HEIGHT);
  }
 catch (  WriterException e) {
    e.printStackTrace();
  }
  return bitmap;
}
