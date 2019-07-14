public void openCamera(Context context){
  boolean image=mConfiguration.isImage();
  Intent captureIntent=image ? new Intent(MediaStore.ACTION_IMAGE_CAPTURE) : new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
  if (captureIntent.resolveActivity(context.getPackageManager()) == null) {
    Toast.makeText(getContext(),R.string.gallery_device_camera_unable,Toast.LENGTH_SHORT).show();
    return;
  }
  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
  String filename=String.format(image ? IMAGE_STORE_FILE_NAME : VIDEO_STORE_FILE_NAME,dateFormat.format(new Date()));
  Logger.i("openCamera?" + mImageStoreDir.getAbsolutePath());
  File fileImagePath=new File(mImageStoreDir,filename);
  mImagePath=fileImagePath.getAbsolutePath();
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagePath));
  }
 else {
    ContentValues contentValues=new ContentValues(1);
    contentValues.put(MediaStore.Images.Media.DATA,mImagePath);
    Uri uri=getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
  }
  startActivityForResult(captureIntent,TAKE_IMAGE_REQUEST_CODE);
}
