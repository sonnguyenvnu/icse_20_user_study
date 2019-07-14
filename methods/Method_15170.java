/** 
 * ????
 * @param fileUri
 * @param width
 * @param height
 */
public void startPhotoZoom(Uri fileUri,int width,int height){
  intent=new Intent("com.android.camera.action.CROP");
  intent.setDataAndType(fileUri,"image/*");
  intent.putExtra("aspectX",1);
  intent.putExtra("aspectY",1);
  intent.putExtra("outputX",width);
  intent.putExtra("outputY",height);
  if (Build.VERSION.SDK_INT >= 23) {
    File outputImage=new File(DataKeeper.imagePath,"output_image" + System.currentTimeMillis() + ".jpg");
    cuttedPicturePath=outputImage.getAbsolutePath();
    intent.putExtra("scale",true);
    intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(outputImage));
  }
 else {
    intent.putExtra("crop","true");
    intent.putExtra("return-data",true);
  }
  Log.i(TAG,"startPhotoZoom  fileUri = " + fileUri);
  toActivity(intent,REQUEST_CUT_PHOTO);
}
