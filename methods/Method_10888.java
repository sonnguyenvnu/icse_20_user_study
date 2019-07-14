/** 
 * ??[?????????,?????????????????????]?Intent
 * @param aspectX     ???????X
 * @param aspectY     ???????Y
 * @param outputX     ??????
 * @param outputY     ??????
 * @param canScale    ?????
 * @param fromFileURI ??????URI
 * @param saveFileURI ??????URI
 */
public static Intent getImagePickerIntent(int aspectX,int aspectY,int outputX,int outputY,boolean canScale,Uri fromFileURI,Uri saveFileURI){
  Intent intent=new Intent(Intent.ACTION_PICK);
  intent.setDataAndType(fromFileURI,"image/*");
  intent.putExtra("crop","true");
  intent.putExtra("aspectX",aspectX <= 0 ? 1 : aspectX);
  intent.putExtra("aspectY",aspectY <= 0 ? 1 : aspectY);
  intent.putExtra("outputX",outputX);
  intent.putExtra("outputY",outputY);
  intent.putExtra("scale",canScale);
  intent.putExtra("scaleUpIfNeeded",true);
  intent.putExtra("return-data",false);
  intent.putExtra(MediaStore.EXTRA_OUTPUT,saveFileURI);
  intent.putExtra("outputFormat",Bitmap.CompressFormat.JPEG.toString());
  return intent.putExtra("noFaceDetection",true);
}
