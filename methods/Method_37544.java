/** 
 * Finds the next file. Returns founded file that matches search configuration or <code>null</code> if no more files can be found.
 */
public File nextFile(){
  if (todoFiles == null) {
    init();
  }
  while (true) {
    if (!todoFiles.isEmpty()) {
      FilesIterator filesIterator=todoFiles.getLast();
      File nextFile=filesIterator.next();
      if (nextFile == null) {
        todoFiles.removeLast();
        continue;
      }
      if (nextFile.isDirectory()) {
        if (!walking) {
          todoFolders.add(nextFile);
          continue;
        }
        if (recursive) {
          todoFiles.add(new FilesIterator(nextFile));
        }
        if (includeDirs) {
          if (acceptFile(nextFile)) {
            lastFile=nextFile;
            return nextFile;
          }
        }
        continue;
      }
      lastFile=nextFile;
      return nextFile;
    }
    File folder;
    boolean initialDir=false;
    if (todoFolders.isEmpty()) {
      if (pathList.isEmpty()) {
        return null;
      }
      folder=pathList.removeFirst();
      rootFile=folder;
      rootPath=rootFile.getAbsolutePath();
      initialDir=true;
    }
 else {
      folder=todoFolders.removeFirst();
    }
    if ((initialDir) || (recursive)) {
      todoFiles.add(new FilesIterator(folder));
    }
    if ((!initialDir) && (includeDirs)) {
      if (acceptFile(folder)) {
        lastFile=folder;
        return folder;
      }
    }
  }
}
