/** 
 * ?UTF8??????.
 */
public static String getFileUTF8(String path){
  String result="";
  InputStream fin=null;
  try {
    fin=new FileInputStream(path);
    int length=fin.available();
    byte[] buffer=new byte[length];
    fin.read(buffer);
    fin.close();
    result=new String(buffer,"UTF-8");
  }
 catch (  Exception e) {
  }
  return result;
}
