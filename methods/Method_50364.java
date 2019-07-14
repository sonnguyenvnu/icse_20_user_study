private void notifyImageToCamera(Context context,Uri uri){
  try {
    File file=new File(uri.getPath());
    MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),file.getName(),null);
  }
 catch (  FileNotFoundException e) {
    e.printStackTrace();
  }
  context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri));
}
