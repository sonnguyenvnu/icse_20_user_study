public static File generatePicturePath(boolean secretChat){
  try {
    File storageDir=getAlbumDir(secretChat);
    Date date=new Date();
    date.setTime(System.currentTimeMillis() + Utilities.random.nextInt(1000) + 1);
    String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss_SSS",Locale.US).format(date);
    return new File(storageDir,"IMG_" + timeStamp + ".jpg");
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return null;
}
