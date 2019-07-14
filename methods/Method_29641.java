/** 
 * ?class???????List<String>
 * @param fileName
 * @return
 */
public static List<String> getListFromFile(String fileName,String encoding){
  List<String> list=new ArrayList<String>();
  InputStream is=null;
  BufferedReader br=null;
  try {
    is=AppController.class.getClassLoader().getResourceAsStream(fileName);
    br=new BufferedReader(new InputStreamReader(is,encoding));
    String line=null;
    while ((line=br.readLine()) != null) {
      list.add(line);
    }
  }
 catch (  IOException e) {
    logger.error(e.getMessage(),e);
  }
 finally {
    if (is != null) {
      try {
        is.close();
      }
 catch (      IOException e) {
        logger.error(e.getMessage(),e);
      }
    }
    if (br != null) {
      try {
        br.close();
      }
 catch (      IOException e) {
        logger.error(e.getMessage(),e);
      }
    }
  }
  return list;
}
