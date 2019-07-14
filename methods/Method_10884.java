/** 
 * ????????uri,??????????
 * @param context
 * @return ???uri
 */
public static Uri createImagePathUri(final Context context){
  final Uri[] imageFilePath={null};
  if (ContextCompat.checkSelfPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions((Activity)context,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    imageFilePath[0]=Uri.parse("");
    RxToast.error("??????SDCard??");
  }
 else {
    String status=Environment.getExternalStorageState();
    SimpleDateFormat timeFormatter=new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.CHINA);
    long time=System.currentTimeMillis();
    String imageName=timeFormatter.format(new Date(time));
    ContentValues values=new ContentValues(3);
    values.put(MediaStore.Images.Media.DISPLAY_NAME,imageName);
    values.put(MediaStore.Images.Media.DATE_TAKEN,time);
    values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");
    if (status.equals(Environment.MEDIA_MOUNTED)) {
      imageFilePath[0]=context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
    }
 else {
      imageFilePath[0]=context.getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI,values);
    }
  }
  Log.i("","??????????" + imageFilePath[0].toString());
  return imageFilePath[0];
}
