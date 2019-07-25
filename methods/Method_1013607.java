/** 
 * From name, prepend path elements to try to find the actual file intended, and use the resulting fully-qualified name to initialize the File.  The first fully-qualified name that refers to a file that actually exists is the File returned; but if none exists, the last one tried is returned anyway.  Hence, the File object returned does not necessarily represent a file that actually exists in the file system.
 * @param module name, used as basis of path name to the file that should contain it
 */
private final File locate(String name){
  String prefix="";
  File sourceFile=null;
  int idx=0;
  InputStream is;
  while (true) {
    if ((idx == 0) && (ToolIO.getUserDir() != null)) {
      sourceFile=new File(ToolIO.getUserDir(),name);
    }
 else {
      if (FilenameToStream.isInJar(prefix)) {
        is=cl.getResourceAsStream(STANDARD_MODULES + name);
        if (is != null) {
          sourceFile=read(name,is);
        }
      }
 else {
        sourceFile=new File(prefix + name);
      }
    }
    if (sourceFile.exists())     break;
    if (idx >= libraryPaths.length) {
      is=cl.getResourceAsStream(name);
      if (is != null) {
        return read(name,is);
      }
 else {
        break;
      }
    }
    prefix=libraryPaths[idx++];
  }
  return sourceFile;
}
