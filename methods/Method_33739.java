/** 
 * gif??????????
 */
private static String getFileName(String mImageUrl,String mImageTitle){
  String fileName;
  if (mImageUrl.contains(".gif")) {
    fileName=mImageTitle.replaceAll("/","-") + ".gif";
  }
 else {
    fileName=mImageTitle.replaceAll("/","-") + ".jpg";
  }
  return fileName;
}
