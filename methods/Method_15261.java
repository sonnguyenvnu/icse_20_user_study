/** 
 * ????
 * @param context
 * @param requestCode
 * @param fromFile
 * @param width
 * @param height
 */
public static void startPhotoZoom(Activity context,int requestCode,Uri fileUri,int width,int height){
  Intent intent=new Intent("com.android.camera.action.CROP");
  intent.setDataAndType(fileUri,"image/*");
  intent.putExtra("crop","true");
  intent.putExtra("aspectX",1);
  intent.putExtra("aspectY",1);
  intent.putExtra("outputX",width);
  intent.putExtra("outputY",height);
  intent.putExtra("return-data",true);
  Log.i(TAG,"startPhotoZoom" + fileUri + " uri");
  toActivity(context,intent,requestCode);
}
