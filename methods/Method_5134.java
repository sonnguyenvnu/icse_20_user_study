/** 
 * Returns the number of bytes that have been loaded. Must only be called after the load completed, failed, or was canceled.
 */
public final long bytesLoaded(){
  return dataSource.getBytesRead();
}
