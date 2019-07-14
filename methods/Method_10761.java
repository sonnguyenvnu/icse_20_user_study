/** 
 * ??????.
 */
public static boolean initFile(String path){
  boolean result=false;
  try {
    File file=new File(path);
    if (!file.exists()) {
      result=file.createNewFile();
    }
 else     if (file.isDirectory()) {
      file.delete();
      result=file.createNewFile();
    }
 else     if (file.exists()) {
      result=true;
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return result;
}
