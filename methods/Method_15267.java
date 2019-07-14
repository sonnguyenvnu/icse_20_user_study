/** 
 * ?????? ????????
 * @param file ??????
 * @param type ????? IMAGE = "imgae";							//??          VIDEO = "video";							//??         VOICE = "voice";							//??          = "voice";							//??         
 * @return	???????????SDCard?????null
 */
public static String storeFile(File file,String type){
  if (!hasSDCard()) {
    return null;
  }
  String suffix=file.getName().substring(file.getName().lastIndexOf(".") + 1);
  byte[] data=null;
  try {
    FileInputStream in=new FileInputStream(file);
    data=new byte[in.available()];
    in.read(data,0,data.length);
    in.close();
  }
 catch (  IOException e) {
    Log.e(TAG,"storeFile  try { FileInputStream in = new FileInputStream(file); ... >>" + " } catch (IOException e) {\n" + e.getMessage());
  }
  return storeFile(data,suffix,type);
}
