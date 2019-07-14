/** 
 * @param f File to be checked against this mode's accepted extensions.
 * @return Whether or not the given file name features an extension supported by this mode.
 */
public boolean canEdit(final File f){
  final int dot=f.getName().lastIndexOf('.');
  if (dot < 0) {
    return false;
  }
  return validExtension(f.getName().substring(dot + 1));
}
