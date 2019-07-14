/** 
 * ????????
 * @param path
 * @return
 */
public static boolean isFileExisted(String path){
  File file=new File(path);
  return file.isFile() && file.exists();
}
