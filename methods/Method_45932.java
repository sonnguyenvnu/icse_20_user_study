/** 
 * ?????????List
 * @param input           ????
 * @param encoding        ??
 * @param blackPrefixList ????????List
 */
private static void readToList(InputStream input,String encoding,List<String> blackPrefixList){
  InputStreamReader reader=null;
  BufferedReader bufferedReader=null;
  try {
    reader=new InputStreamReader(input,encoding);
    bufferedReader=new BufferedReader(reader);
    String lineText;
    while ((lineText=bufferedReader.readLine()) != null) {
      String pkg=lineText.trim();
      if (pkg.length() > 0) {
        blackPrefixList.add(pkg);
      }
    }
  }
 catch (  IOException e) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn(e.getMessage(),e);
    }
  }
 finally {
    closeQuietly(bufferedReader);
    closeQuietly(reader);
  }
}
