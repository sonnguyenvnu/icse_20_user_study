/** 
 * Get Content-disposition parameter: filename
 * @param fileName the filename value
 * @throws NullPointerException if the value is null.
 */
public void setFilename(byte[] fileName){
  if (null == fileName) {
    throw new NullPointerException("null content-id");
  }
  mPartHeader.put(P_FILENAME,fileName);
}
