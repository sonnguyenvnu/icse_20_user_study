/** 
 * Returns the number of bytes loaded. In the case that the network response was compressed, the value returned is the size of the data <em>after</em> decompression. Must only be called after the load completed, failed, or was canceled.
 */
public long bytesLoaded(){
  return dataSource.getBytesRead();
}
