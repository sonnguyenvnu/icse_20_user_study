/** 
 * Return whether the shortName is part of the compound name by itself or as a method call receiver.
 */
private static boolean isReference(String shortName,String compoundName){
  int dotPos=compoundName.indexOf('.');
  return dotPos < 0 ? shortName.equals(compoundName) : shortName.equals(compoundName.substring(0,dotPos));
}
