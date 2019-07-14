/** 
 * Check this extension (no dots, please) against the list of valid extensions.
 */
public boolean validExtension(String what){
  String[] ext=getExtensions();
  for (int i=0; i < ext.length; i++) {
    if (ext[i].equals(what))     return true;
  }
  return false;
}
