/** 
 * Decompress files or dirs inside the compressed file.
 * @param subDirectories separator is "/", ended with "/" if it is a directory, does not if it's a file
 */
public final void decompress(String whereToDecompress,String[] subDirectories){
  for (int i=0; i < subDirectories.length; i++) {
    subDirectories[i]=realRelativeDirectory(subDirectories[i]);
  }
  Intent intent=new Intent(context,ExtractService.class);
  intent.putExtra(ExtractService.KEY_PATH_ZIP,filePath);
  intent.putExtra(ExtractService.KEY_ENTRIES_ZIP,subDirectories);
  intent.putExtra(ExtractService.KEY_PATH_EXTRACT,whereToDecompress);
  ServiceWatcherUtil.runService(context,intent);
}
