/** 
 * Returns the number of downloads. 
 */
public int getDownloadCount(){
  Assertions.checkState(!released);
  return downloads.size();
}
