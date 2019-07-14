/** 
 * Method calculates zip file size to initiate progress Supporting local file extraction progress for now
 */
private long getTotalSize(String filePath){
  return new File(filePath).length();
}
