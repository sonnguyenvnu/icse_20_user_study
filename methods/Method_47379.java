/** 
 * Get the files at a specific path in OTG
 * @param path    the path to the directory tree, starts with prefix 'otg:/'Independent of URI (or mount point) for the OTG
 * @param context context for loading
 */
public static void getDocumentFiles(String path,Context context,OnFileFound fileFound){
  Uri rootUriString=SingletonUsbOtg.getInstance().getUsbOtgRoot();
  if (rootUriString == null)   throw new NullPointerException("USB OTG root not set!");
  DocumentFile rootUri=DocumentFile.fromTreeUri(context,rootUriString);
  String[] parts=path.split("/");
  for (  String part : parts) {
    if (path.equals(OTGUtil.PREFIX_OTG + "/"))     break;
    if (part.equals("otg:") || part.equals(""))     continue;
    rootUri=rootUri.findFile(part);
  }
  for (  DocumentFile file : rootUri.listFiles()) {
    if (file.exists()) {
      long size=0;
      if (!file.isDirectory())       size=file.length();
      Log.d(context.getClass().getSimpleName(),"Found file: " + file.getName());
      HybridFileParcelable baseFile=new HybridFileParcelable(path + "/" + file.getName(),RootHelper.parseDocumentFilePermission(file),file.lastModified(),size,file.isDirectory());
      baseFile.setName(file.getName());
      baseFile.setMode(OpenMode.OTG);
      fileFound.onFileFound(baseFile);
    }
  }
}
