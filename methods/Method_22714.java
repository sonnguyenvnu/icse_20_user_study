/** 
 * Returns true if this is a read-only sketch. Used for the examples directory, or when sketches are loaded from read-only volumes or folders without appropriate permissions.
 */
public boolean isReadOnly(){
  String apath=folder.getAbsolutePath();
  List<Mode> modes=editor.getBase().getModeList();
  for (  Mode mode : modes) {
    if (apath.startsWith(mode.getExamplesFolder().getAbsolutePath()) || apath.startsWith(mode.getLibrariesFolder().getAbsolutePath())) {
      return true;
    }
  }
  for (int i=0; i < codeCount; i++) {
    if (code[i].isModified() && code[i].fileReadOnly() && code[i].fileExists()) {
      return true;
    }
  }
  return false;
}
