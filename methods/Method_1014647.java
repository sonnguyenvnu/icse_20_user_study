/** 
 * @param filePath
 * @param config
 * @return
 * @version 1.0.0
 */
public static XWPFTemplate compile(String filePath,Configure config){
  return compile(new File(filePath),config);
}
