/** 
 * Initializes dir watcher by reading all files from watched folder.
 */
protected void init(){
  File[] filesArray=dir.listFiles();
  filesCount=0;
  if (filesArray != null) {
    filesCount=filesArray.length;
    for (    File file : filesArray) {
      if (!acceptFile(file)) {
        continue;
      }
      map.put(file,new MutableLong(file.lastModified()));
    }
  }
}
