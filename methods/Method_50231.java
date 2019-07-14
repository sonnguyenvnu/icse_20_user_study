/** 
 * ???? <p> fragment ?? activity ????  this?  ???? <p> ??? Fragment ??? v4???Fragment
 * @see Fragment <p> M ????????????????????????????? <p> ???? -1 ? ??????
 */
public static int openZKCamera(Object activity){
  if (activity == null) {
    throw new NullPointerException("activity == null");
  }
  Activity cameraActivity=null;
  if (activity instanceof Activity) {
    cameraActivity=(Activity)activity;
  }
  if (activity instanceof Fragment) {
    Fragment fragment=(Fragment)activity;
    cameraActivity=fragment.getActivity();
  }
  assert cameraActivity != null;
  Intent captureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
  if (captureIntent.resolveActivity(cameraActivity.getPackageManager()) != null) {
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
    String imageName="immqy_%s.jpg";
    String filename=String.format(imageName,dateFormat.format(new Date()));
    File mImageStoreDir=new File(Environment.getExternalStorageDirectory(),"/DCIM/IMMQY/");
    if (!mImageStoreDir.exists()) {
      mImageStoreDir.mkdirs();
    }
    fileImagePath=new File(mImageStoreDir,filename);
    String mImagePath=fileImagePath.getAbsolutePath();
    Logger.i("->mImagePath:" + mImagePath);
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
      captureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagePath));
    }
 else {
      ContentValues contentValues=new ContentValues(1);
      contentValues.put(MediaStore.Images.Media.DATA,mImagePath);
      contentValues.put(MediaStore.Images.Media.MIME_TYPE,IMG_TYPE);
      Uri uri=cameraActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
      captureIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
    }
    if (activity instanceof Activity) {
      cameraActivity.startActivityForResult(captureIntent,TAKE_IMAGE_REQUEST_CODE);
    }
    if (activity instanceof Fragment) {
      Fragment fragment=(Fragment)activity;
      fragment.startActivityForResult(captureIntent,TAKE_IMAGE_REQUEST_CODE);
    }
    return 0;
  }
 else {
    Toast.makeText(cameraActivity,R.string.gallery_device_camera_unable,Toast.LENGTH_SHORT).show();
    return -1;
  }
}
