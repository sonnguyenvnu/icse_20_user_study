/** 
 * ????????????, ??????.
 */
public static boolean delAllFile(String path){
  boolean flag=false;
  File file=new File(path);
  if (!file.exists()) {
    return flag;
  }
  if (file.isFile()) {
    file.delete();
    return true;
  }
  File[] files=file.listFiles();
  for (int i=0; i < files.length; i++) {
    File exeFile=files[i];
    if (exeFile.isDirectory()) {
      delAllFile(exeFile.getAbsolutePath());
    }
 else {
      exeFile.delete();
    }
  }
  file.delete();
  return flag;
}
