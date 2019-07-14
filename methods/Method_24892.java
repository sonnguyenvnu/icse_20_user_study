/** 
 * Looks for the specified method in the base scope of the search area.
 */
static protected String[] matchMethod(String methodName,String searchArea){
  final String left="(?:^|\\s|;)";
  final String right="\\s*\\(([^\\)]*)\\)\\s*;";
  String regexp=left + methodName + right;
  Pattern p=matchPatterns.get(regexp);
  if (p == null) {
    p=Pattern.compile(regexp,Pattern.MULTILINE | Pattern.DOTALL);
    matchPatterns.put(regexp,p);
  }
  MatchResult match=findInCurrentScope(p,searchArea);
  if (match != null) {
    int count=match.groupCount() + 1;
    String[] groups=new String[count];
    for (int i=0; i < count; i++) {
      groups[i]=match.group(i);
    }
    return groups;
  }
  return null;
}
