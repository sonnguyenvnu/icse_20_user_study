/** 
 * ????????
 * @return ??????
 */
public static String getBaseDirName(){
  String fileName=null;
  java.net.URL url1=FileUtils.class.getResource("/");
  if (url1 != null) {
    fileName=url1.getFile();
  }
 else {
    String jarpath=ReflectUtils.getCodeBase(FileUtils.class);
    if (jarpath != null) {
      int sofaidx=jarpath.lastIndexOf("sofa");
      if (sofaidx > -1) {
        fileName=jarpath.substring(0,sofaidx);
      }
 else {
        int sepidx=jarpath.lastIndexOf(File.separator);
        if (sepidx > -1) {
          fileName=jarpath.substring(0,sepidx + 1);
        }
      }
    }
  }
  if (fileName != null) {
    fileName=fileName.replace(":","").replace(File.separator,"/").replace("/","-");
    if (fileName.startsWith("-")) {
      fileName=fileName.substring(1);
    }
  }
 else {
    fileName="UNKNOW_";
  }
  return fileName;
}
