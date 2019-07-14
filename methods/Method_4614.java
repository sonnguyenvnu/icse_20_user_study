/** 
 * Parses the version number out of the additional integer component of a full atom.
 */
public static int parseFullAtomVersion(int fullAtomInt){
  return 0x000000FF & (fullAtomInt >> 24);
}
