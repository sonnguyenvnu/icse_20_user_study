private File getVideoFile(){
  if (!Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
    return null;
  }
  File mediaStorageDir=new File(Environment.getExternalStorageDirectory(),"Camera");
  if (!mediaStorageDir.exists()) {
    if (!mediaStorageDir.mkdirs()) {
      return null;
    }
  }
  return new File(mediaStorageDir.getPath() + File.separator + "video.mp4");
}
