/** 
 * Creates bundle file in bundleFolder/staplerPath. Only file object is created, not the file content.
 */
protected File createBundleFile(final String bundleId){
  File folder=new File(bundleFolder,staplerPath);
  if (!folder.exists()) {
    folder.mkdirs();
  }
  return new File(folder,bundleId);
}
