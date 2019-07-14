/** 
 * Determine if we're dealing with .size method
 * @param occ The name occurrence
 * @return true if it's .size, else false
 */
@Override public boolean isTargetMethod(JavaNameOccurrence occ){
  if (occ.getNameForWhichThisIsAQualifier() != null) {
    if (occ.getLocation().getImage().endsWith(".size")) {
      return true;
    }
  }
  return false;
}
