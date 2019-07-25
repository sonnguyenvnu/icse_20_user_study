/** 
 * Searches for the file in current directory (ToolIO.userDirectory) and in library paths
 * @param name - name of the file to look for
 * @return a File handle. Even if the file does not exist, the handle is not null
 */
private final File locate(String name){
  String prefix="";
  File sourceFile=null;
  int idx=0;
  while (true) {
    if ((idx == 0) && (ToolIO.getUserDir() != null)) {
      sourceFile=new File(ToolIO.getUserDir(),name);
    }
 else {
      if (FilenameToStream.isArchive(prefix)) {
        sourceFile=getFromArchive(prefix,name);
        if (sourceFile != null) {
          return sourceFile;
        }
      }
 else {
        sourceFile=new File(prefix + name);
      }
    }
    if (sourceFile != null && sourceFile.exists()) {
      break;
    }
    if (idx >= libraryPathEntries.size()) {
      break;
    }
    prefix=(String)libraryPathEntries.elementAt(idx++);
  }
  return sourceFile;
}
