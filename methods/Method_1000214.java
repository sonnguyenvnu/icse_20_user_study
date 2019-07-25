private static Bitmap copy(Bitmap bitmap){
  Bitmap.Config config=bitmap.getConfig();
  if (config == null) {
    config=Bitmap.Config.RGB_565;
  }
  try {
    return bitmap.copy(config,false);
  }
 catch (  OutOfMemoryError e) {
    e.printStackTrace();
    return null;
  }
}
