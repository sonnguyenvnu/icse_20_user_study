private File getDownloadTarget(){
  try {
    if (downloadTarget == null) {
      downloadTarget=File.createTempFile("download_","_resume",getCacheDir());
    }
  }
 catch (  IOException e) {
    Log.e(LOG_TAG,"Couldn't create cache file to download to");
  }
  return downloadTarget;
}
