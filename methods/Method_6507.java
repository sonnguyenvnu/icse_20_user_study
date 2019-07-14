public SparseArray<File> createMediaPaths(){
  SparseArray<File> mediaDirs=new SparseArray<>();
  File cachePath=AndroidUtilities.getCacheDir();
  if (!cachePath.isDirectory()) {
    try {
      cachePath.mkdirs();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  try {
    new File(cachePath,".nomedia").createNewFile();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  mediaDirs.put(FileLoader.MEDIA_DIR_CACHE,cachePath);
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("cache path = " + cachePath);
  }
  try {
    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
      telegramPath=new File(Environment.getExternalStorageDirectory(),"Telegram");
      telegramPath.mkdirs();
      if (telegramPath.isDirectory()) {
        try {
          File imagePath=new File(telegramPath,"Telegram Images");
          imagePath.mkdir();
          if (imagePath.isDirectory() && canMoveFiles(cachePath,imagePath,FileLoader.MEDIA_DIR_IMAGE)) {
            mediaDirs.put(FileLoader.MEDIA_DIR_IMAGE,imagePath);
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("image path = " + imagePath);
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        try {
          File videoPath=new File(telegramPath,"Telegram Video");
          videoPath.mkdir();
          if (videoPath.isDirectory() && canMoveFiles(cachePath,videoPath,FileLoader.MEDIA_DIR_VIDEO)) {
            mediaDirs.put(FileLoader.MEDIA_DIR_VIDEO,videoPath);
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("video path = " + videoPath);
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        try {
          File audioPath=new File(telegramPath,"Telegram Audio");
          audioPath.mkdir();
          if (audioPath.isDirectory() && canMoveFiles(cachePath,audioPath,FileLoader.MEDIA_DIR_AUDIO)) {
            new File(audioPath,".nomedia").createNewFile();
            mediaDirs.put(FileLoader.MEDIA_DIR_AUDIO,audioPath);
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("audio path = " + audioPath);
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        try {
          File documentPath=new File(telegramPath,"Telegram Documents");
          documentPath.mkdir();
          if (documentPath.isDirectory() && canMoveFiles(cachePath,documentPath,FileLoader.MEDIA_DIR_DOCUMENT)) {
            new File(documentPath,".nomedia").createNewFile();
            mediaDirs.put(FileLoader.MEDIA_DIR_DOCUMENT,documentPath);
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("documents path = " + documentPath);
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("this Android can't rename files");
      }
    }
    SharedConfig.checkSaveToGalleryFiles();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return mediaDirs;
}
