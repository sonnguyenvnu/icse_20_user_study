/** 
 * ????????????
 * @param path
 * @return
 */
public static LinkedList<String> readLineListWithLessMemory(String path){
  LinkedList<String> result=new LinkedList<String>();
  String line=null;
  boolean first=true;
  try {
    BufferedReader bw=new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path),"UTF-8"));
    while ((line=bw.readLine()) != null) {
      if (first) {
        first=false;
        if (!line.isEmpty() && line.charAt(0) == '\uFEFF')         line=line.substring(1);
      }
      result.add(line);
    }
    bw.close();
  }
 catch (  Exception e) {
    logger.warning("??" + path + "???" + e);
  }
  return result;
}
