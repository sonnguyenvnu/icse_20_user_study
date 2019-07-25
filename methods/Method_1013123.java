/** 
 * Tries to find the specified module. Starts in work directory and then looks up in to the library paths for modules.
 * @see {@link util.FilenameResolver#resolve(java.lang.String,boolean)}
 */
public File resolve(String name,boolean isModule){
  if (isModule && name.endsWith(".tla")) {
    name=name.substring(0,name.length() - 4);
  }
  String sourceFileName;
  if (isModule) {
    sourceFileName=name + ".tla";
  }
 else {
    sourceFileName=name;
  }
  File sourceFile=locate(sourceFileName);
  return sourceFile;
}
