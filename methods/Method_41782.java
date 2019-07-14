/** 
 * @see org.quartz.jobs.FileScanListener#fileUpdated(java.lang.String)
 */
public void fileUpdated(String fileName){
  if (started) {
    processFile(fileName);
  }
}
