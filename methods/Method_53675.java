/** 
 * @description???????
 * @param filePath :????????
 * @return ??????
 * @author tanshuguo
 */
public static String getFileContent(String filePath){
  StringBuffer sb=new StringBuffer();
  InputStreamReader isr=null;
  BufferedReader bufferedReader=null;
  try {
    String encoding="utf-8";
    File file=new File(filePath);
    if (file.isFile() && file.exists()) {
      isr=new InputStreamReader(new FileInputStream(file),encoding);
      bufferedReader=new BufferedReader(isr);
      String lineTxt=null;
      while ((lineTxt=bufferedReader.readLine()) != null) {
        sb.append(lineTxt);
      }
      isr.close();
    }
 else {
      System.out.println("????????");
    }
  }
 catch (  Exception e) {
    System.out.println("????????");
    e.printStackTrace();
  }
 finally {
    try {
      if (isr != null) {
        isr.close();
        isr=null;
      }
      if (bufferedReader != null) {
        bufferedReader.close();
        bufferedReader=null;
      }
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  return sb.toString();
}
