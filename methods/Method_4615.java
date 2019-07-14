/** 
 * Parses the atom flags out of the additional integer component of a full atom.
 */
public static int parseFullAtomFlags(int fullAtomInt){
  return 0x00FFFFFF & fullAtomInt;
}
