/** 
 * Gets dimension key.
 * @return the dimension key
 */
public String getDimensionKey(){
  if (dimensionKey == null) {
    dimensionKey=getAppName() + ":" + getService();
  }
  return dimensionKey;
}
