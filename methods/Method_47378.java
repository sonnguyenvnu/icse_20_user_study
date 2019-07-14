/** 
 * Returns an array of list of files at a specific path in OTG
 * @param path    the path to the directory tree, starts with prefix 'otg:/'Independent of URI (or mount point) for the OTG
 * @param context context for loading
 * @return an array of list of files at the path
 * @deprecated use getDocumentFiles()
 */
public static ArrayList<HybridFileParcelable> getDocumentFilesList(String path,Context context){
  final ArrayList<HybridFileParcelable> files=new ArrayList<>();
  getDocumentFiles(path,context,files::add);
  return files;
}
