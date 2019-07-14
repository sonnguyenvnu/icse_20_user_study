/** 
 * Add import statements to the current tab for all of packages inside the specified jar file.
 */
public void handleImportLibrary(String libraryName){
  sketch.ensureExistence();
  if (mode.isDefaultExtension(sketch.getCurrentCode())) {
    sketch.setCurrentCode(0);
  }
  Library lib=mode.findLibraryByName(libraryName);
  if (lib == null) {
    statusError("Unable to locate library: " + libraryName);
    return;
  }
  StringList list=lib.getImports();
  if (list == null) {
    list=Util.packageListFromClassPath(lib.getJarPath());
  }
  StringBuilder sb=new StringBuilder();
  for (  String item : list) {
    sb.append("import ");
    sb.append(item);
    sb.append(".*;\n");
  }
  sb.append('\n');
  sb.append(getText());
  setText(sb.toString());
  setSelection(0,0);
  sketch.setModified(true);
}
