/** 
 * Decompress a file somewhere
 */
public final void decompress(String whereToDecompress){
  Intent intent=new Intent(context,ExtractService.class);
  intent.putExtra(ExtractService.KEY_PATH_ZIP,filePath);
  intent.putExtra(ExtractService.KEY_ENTRIES_ZIP,new String[0]);
  intent.putExtra(ExtractService.KEY_PATH_EXTRACT,whereToDecompress);
  ServiceWatcherUtil.runService(context,intent);
}
