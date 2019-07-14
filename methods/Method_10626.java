/** 
 * @param content   ????????
 * @param QR_WIDTH  ??????
 * @param QR_HEIGHT ??????
 * @param iv_code   ????
 */
public static void createQRCode(String content,int QR_WIDTH,int QR_HEIGHT,ImageView iv_code){
  iv_code.setImageBitmap(creatQRCode(content,QR_WIDTH,QR_HEIGHT));
}
