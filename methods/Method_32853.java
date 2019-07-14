/** 
 * Get the document id given a file. This document id must be consistent across time as other applications may save the ID and use it to reference documents later. <p/> The reverse of @{link #getFileForDocId}.
 */
private static String getDocIdForFile(File file){
  return file.getAbsolutePath();
}
