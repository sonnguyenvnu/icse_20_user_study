/** 
 * ??[???????]?Intent
 */
public static Intent getCropImageIntent(int aspectX,int aspectY,int outputX,int outputY,boolean canScale,Uri fromFileURI,Uri saveFileURI){
  Intent intent=new Intent("com.android.camera.action.CROP");
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
  intent.putExtra("noFaceDetection",true);
  return intent;
}
