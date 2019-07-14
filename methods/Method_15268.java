/** 
 * @return	???????????SDCard?????null 
 */
@SuppressLint("DefaultLocale") public static String storeFile(byte[] data,String suffix,String type){
  if (!hasSDCard()) {
    return null;
  }
  String path=null;
  if (type.equals(TYPE_FILE_IMAGE)) {
    path=imagePath + "IMG_" + Long.toHexString(System.currentTimeMillis()).toUpperCase() + "." + suffix;
  }
 else   if (type.equals(TYPE_FILE_VIDEO)) {
    path=videoPath + "VIDEO_" + Long.toHexString(System.currentTimeMillis()).toUpperCase() + "." + suffix;
  }
 else   if (type.equals(TYPE_FILE_AUDIO)) {
    path=audioPath + "VOICE_" + Long.toHexString(System.currentTimeMillis()).toUpperCase() + "." + suffix;
  }
  try {
    FileOutputStream out=new FileOutputStream(path);
    out.write(data,0,data.length);
    out.close();
  }
 catch (  FileNotFoundException e) {
    Log.e(TAG,"storeFile  try { FileInputStream in = new FileInputStream(file); ... >>" + " } catch (FileNotFoundException e) {\n" + e.getMessage() + "\n\n >> path = null;");
    path=null;
  }
catch (  IOException e) {
    Log.e(TAG,"storeFile  try { FileInputStream in = new FileInputStream(file); ... >>" + " } catch (IOException e) {\n" + e.getMessage() + "\n\n >> path = null;");
    path=null;
  }
  return path;
}
