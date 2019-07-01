/** 
 * Remove all entries for this log file in each thread's cache.
 * @param logId
 */
public void _XXXXX_(long logId){
  FileChannel fileChannel=logid2FileChannel.remove(logId);
  if (null != fileChannel) {
    try {
      fileChannel.close();
    }
 catch (    IOException e) {
      LOG.warn("Exception while closing channel for log file:" + logId);
    }
  }
}