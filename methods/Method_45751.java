/** 
 * ?????????
 * @param clazz        ??
 * @param relativePath ????
 * @param encoding     ??
 * @return ????
 * @throws IOException ??IO??
 */
public static String file2String(Class clazz,String relativePath,String encoding) throws IOException {
  InputStream is=null;
  InputStreamReader reader=null;
  BufferedReader bufferedReader=null;
  try {
    is=clazz.getResourceAsStream(relativePath);
    reader=new InputStreamReader(is,encoding);
    bufferedReader=new BufferedReader(reader);
    StringBuilder context=new StringBuilder();
    String lineText;
    while ((lineText=bufferedReader.readLine()) != null) {
      context.append(lineText).append(LINE_SEPARATOR);
    }
    return context.toString();
  }
  finally {
    if (bufferedReader != null) {
      bufferedReader.close();
    }
    if (reader != null) {
      reader.close();
    }
    if (is != null) {
      is.close();
    }
  }
}
