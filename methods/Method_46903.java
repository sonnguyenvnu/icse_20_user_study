/** 
 * Lists files from an OTG device
 * @param path the path to the directory tree, starts with prefix {@link com.amaze.filemanager.utils.OTGUtil#PREFIX_OTG}Independent of URI (or mount point) for the OTG
 */
private void listOtg(String path,OnFileFound fileFound){
  OTGUtil.getDocumentFiles(path,c,fileFound);
}
