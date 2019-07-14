/** 
 * ?????Bitmap
 * @param content   ??
 * @param widthPix  ????
 * @param heightPix ????
 * @param logoBm    ??????Logo??????null?
 * @param filePath  ??????????????
 * @return ??????????????
 */
public static boolean createQRImage(String content,int widthPix,int heightPix,Bitmap logoBm,String filePath){
  try {
    if (content == null || "".equals(content)) {
      return false;
    }
    Map<EncodeHintType,Object> hints=new HashMap<>();
    hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
    hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.H);
    BitMatrix bitMatrix=new QRCodeWriter().encode(content,BarcodeFormat.QR_CODE,widthPix,heightPix,hints);
    int[] pixels=new int[widthPix * heightPix];
    for (int y=0; y < heightPix; y++) {
      for (int x=0; x < widthPix; x++) {
        if (bitMatrix.get(x,y)) {
          pixels[y * widthPix + x]=0xff000000;
        }
 else {
          pixels[y * widthPix + x]=0xffffffff;
        }
      }
    }
    Bitmap bitmap=Bitmap.createBitmap(widthPix,heightPix,Bitmap.Config.ARGB_8888);
    bitmap.setPixels(pixels,0,widthPix,0,0,widthPix,heightPix);
    if (logoBm != null) {
      bitmap=addLogo(bitmap,logoBm);
    }
    return bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(filePath));
  }
 catch (  WriterException|IOException e) {
    e.printStackTrace();
  }
  return false;
}
