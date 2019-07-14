public void openCamera(){
  if (parentFragment == null || parentFragment.getParentActivity() == null) {
    return;
  }
  try {
    if (Build.VERSION.SDK_INT >= 23 && parentFragment.getParentActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
      parentFragment.getParentActivity().requestPermissions(new String[]{Manifest.permission.CAMERA},19);
      return;
    }
    Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    File image=AndroidUtilities.generatePicturePath();
    if (image != null) {
      if (Build.VERSION.SDK_INT >= 24) {
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,FileProvider.getUriForFile(parentFragment.getParentActivity(),BuildConfig.APPLICATION_ID + ".provider",image));
        takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      }
 else {
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(image));
      }
      currentPicturePath=image.getAbsolutePath();
    }
    parentFragment.startActivityForResult(takePictureIntent,13);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
