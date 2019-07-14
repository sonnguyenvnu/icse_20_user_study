private static File getAlbumDir(boolean secretChat){
  if (secretChat || Build.VERSION.SDK_INT >= 23 && ApplicationLoader.applicationContext.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
    return FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE);
  }
  File storageDir=null;
  if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
    storageDir=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Telegram");
    if (!storageDir.mkdirs()) {
      if (!storageDir.exists()) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("failed to create directory");
        }
        return null;
      }
    }
  }
 else {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("External storage is not mounted READ/WRITE.");
    }
  }
  return storageDir;
}
