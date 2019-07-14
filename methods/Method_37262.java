/** 
 * Extract index string from non-nested property name. If index is found, it is stripped from bean property name. If no index is found, it returns <code>null</code>.
 */
protected String extractIndex(final BeanProperty bp){
  bp.index=null;
  String name=bp.name;
  int lastNdx=name.length() - 1;
  if (lastNdx < 0) {
    return null;
  }
  if (name.charAt(lastNdx) == ']') {
    int leftBracketNdx=name.lastIndexOf('[');
    if (leftBracketNdx != -1) {
      bp.setName(name.substring(0,leftBracketNdx));
      bp.index=name.substring(leftBracketNdx + 1,lastNdx);
      return bp.index;
    }
  }
  return null;
}
