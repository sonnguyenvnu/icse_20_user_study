/** 
 * @deprecated use getCloudFiles()
 */
public static ArrayList<HybridFileParcelable> listFiles(String path,CloudStorage cloudStorage,OpenMode openMode) throws CloudPluginException {
  final ArrayList<HybridFileParcelable> baseFiles=new ArrayList<>();
  getCloudFiles(path,cloudStorage,openMode,baseFiles::add);
  return baseFiles;
}
