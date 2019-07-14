/** 
 * List who's inside a windows64, macosx, linux32, etc folder.
 */
static String[] listPlatformEntries(File libraryFolder,String folderName,String[] baseList){
  File folder=new File(libraryFolder,folderName);
  if (folder.exists()) {
    String[] entries=folder.list(standardFilter);
    if (entries != null) {
      String[] outgoing=new String[entries.length + baseList.length];
      for (int i=0; i < entries.length; i++) {
        outgoing[i]=folderName + "/" + entries[i];
      }
      System.arraycopy(baseList,0,outgoing,entries.length,baseList.length);
      return outgoing;
    }
  }
  return null;
}
