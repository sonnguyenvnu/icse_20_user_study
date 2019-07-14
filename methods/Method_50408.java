/** 
 * Gets full file name.
 * @param filePath the file path
 * @param id       the id
 * @return the full file name
 */
public static String getFullFileName(final String filePath,final String id){
  return String.format("%s/%s",filePath,id);
}
