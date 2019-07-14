public void openCamera(){
  Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
  imagePath=Uri.fromFile(getDiskCacheDir());
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
    intent.putExtra(MediaStore.EXTRA_OUTPUT,imagePath);
  }
 else {
    ContentValues contentValues=new ContentValues(1);
    contentValues.put(MediaStore.Images.Media.DATA,imagePath.getPath());
    contentValues.put(MediaStore.Images.Media.MIME_TYPE,IMAGE_TYPE);
    Uri uri=listener.getSimpleActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
    intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
  }
  listener.getSimpleActivity().startActivityForResult(intent,TYPE_CAMERA);
}
