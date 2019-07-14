/** 
 * ???????????  
 */
public static String getFileCachePath(int fileType,String fileName,String formSuffix){
switch (fileType) {
case TYPE_FILE_IMAGE:
    return imagePath + fileName + "." + formSuffix;
case TYPE_FILE_VIDEO:
  return videoPath + fileName + "." + formSuffix;
case TYPE_FILE_AUDIO:
return audioPath + fileName + "." + formSuffix;
default :
return tempPath + fileName + "." + formSuffix;
}
}
