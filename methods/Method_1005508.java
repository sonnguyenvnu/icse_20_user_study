/** 
 * Determines if two property sources are different.
 * @param mp1 map property sources 1
 * @param mp2 map property sources 2
 * @return {@code true} if source has changed
 */
protected boolean changed(MapPropertySource mp1,MapPropertySource mp2){
  if (mp1 == mp2) {
    return false;
  }
  if (mp1 == null && mp2 != null || mp1 != null && mp2 == null) {
    return true;
  }
  Map<String,Object> s1=mp1.getSource();
  Map<String,Object> s2=mp2.getSource();
  return s1 == null ? s2 != null : !s1.equals(s2);
}
