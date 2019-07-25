/** 
 * template file as InputStream
 * @param inputStream
 * @return
 * @version 1.2.0
 */
public static XWPFTemplate compile(InputStream inputStream){
  return compile(inputStream,Configure.createDefault());
}
