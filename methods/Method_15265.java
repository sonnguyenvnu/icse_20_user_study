public static void init(Context context_){
  context=context_;
  Log.i(TAG,"init fileRootPath = " + fileRootPath);
  if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
    if (fileRootPath != null) {
      File file=new File(imagePath);
      if (!file.exists()) {
        file.mkdirs();
      }
      file=new File(videoPath);
      if (!file.exists()) {
        file.mkdir();
      }
      file=new File(audioPath);
      if (!file.exists()) {
        file.mkdir();
      }
      file=new File(fileRootPath + accountPath);
      if (!file.exists()) {
        file.mkdir();
      }
      file=new File(tempPath);
      if (!file.exists()) {
        file.mkdir();
      }
    }
  }
}
