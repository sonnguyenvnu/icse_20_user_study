public static void checkSaveToGalleryFiles(){
  try {
    File telegramPath=new File(Environment.getExternalStorageDirectory(),"Telegram");
    File imagePath=new File(telegramPath,"Telegram Images");
    imagePath.mkdir();
    File videoPath=new File(telegramPath,"Telegram Video");
    videoPath.mkdir();
    if (saveToGallery) {
      if (imagePath.isDirectory()) {
        new File(imagePath,".nomedia").delete();
      }
      if (videoPath.isDirectory()) {
        new File(videoPath,".nomedia").delete();
      }
    }
 else {
      if (imagePath.isDirectory()) {
        new File(imagePath,".nomedia").createNewFile();
      }
      if (videoPath.isDirectory()) {
        new File(videoPath,".nomedia").createNewFile();
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
