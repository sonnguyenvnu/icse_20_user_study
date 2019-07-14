/** 
 * Returns true if the contribution is a starred/recommended contribution, or is by the Processing Foundation.
 */
boolean isSpecial(){
  if (authors != null && authors.contains(FOUNDATION_AUTHOR)) {
    return true;
  }
  if (categories != null && categories.hasValue(SPECIAL_CATEGORY)) {
    return true;
  }
  return false;
}
