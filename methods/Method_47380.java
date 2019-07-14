/** 
 * Traverse to a specified path in OTG
 * @param createRecursive flag used to determine whether to create new file while traversing to path,in case path is not present. Notably useful in opening an output stream.
 */
public static DocumentFile getDocumentFile(String path,Context context,boolean createRecursive){
  Uri rootUriString=SingletonUsbOtg.getInstance().getUsbOtgRoot();
  if (rootUriString == null)   throw new NullPointerException("USB OTG root not set!");
  DocumentFile rootUri=DocumentFile.fromTreeUri(context,rootUriString);
  String[] parts=path.split("/");
  for (  String part : parts) {
    if (path.equals("otg:/"))     break;
    if (part.equals("otg:") || part.equals(""))     continue;
    DocumentFile nextDocument=rootUri.findFile(part);
    if (createRecursive && (nextDocument == null || !nextDocument.exists())) {
      nextDocument=rootUri.createFile(part.substring(part.lastIndexOf(".")),part);
    }
    rootUri=nextDocument;
  }
  return rootUri;
}
