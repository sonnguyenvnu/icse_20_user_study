/** 
 * Zxing???????
 * @param contents ??
 * @param barcodeFormat BarcodeFormat??
 * @param format ???????[png,jpg,bmp]
 * @param width ?
 * @param height ?
 * @param margin ????px
 * @param saveImgFilePath ???????????????
 * @return {boolean}
 */
public static boolean encode(String contents,BarcodeFormat barcodeFormat,Integer margin,ErrorCorrectionLevel errorLevel,String format,int width,int height,String saveImgFilePath){
  Boolean bool=false;
  BufferedImage bufImg;
  Map<EncodeHintType,Object> hints=new HashMap<EncodeHintType,Object>();
  hints.put(EncodeHintType.ERROR_CORRECTION,errorLevel);
  hints.put(EncodeHintType.MARGIN,margin);
  hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
  try {
    BitMatrix bitMatrix=new MultiFormatWriter().encode(contents,barcodeFormat,width,height,hints);
    MatrixToImageConfig config=new MatrixToImageConfig(0xFF000001,0xFFFFFFFF);
    bufImg=MatrixToImageWriter.toBufferedImage(bitMatrix,config);
    bool=writeToFile(bufImg,format,saveImgFilePath);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return bool;
}
