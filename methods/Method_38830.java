/** 
 * Returns true if attribute is containing some value.
 */
public boolean isContaining(final String include){
  if (value == null) {
    return false;
  }
  if (splits == null) {
    splits=StringUtil.splitc(value,' ');
  }
  for (  String s : splits) {
    if (s.equals(include)) {
      return true;
    }
  }
  return false;
}
