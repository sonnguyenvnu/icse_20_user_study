/** 
 * Check if any of the rules say anything about the specified path
 * @param path The path to check
 * @return One of ALLOWED, DISALLOWED or UNDEFINED
 */
public int checkAccess(String path){
  timeLastAccessed=System.currentTimeMillis();
  int result=UNDEFINED;
  String myUA=config.getUserAgentName();
  boolean ignoreUADisc=config.getIgnoreUADiscrimination();
  for (  UserAgentDirectives ua : rules) {
    int score=ua.match(myUA);
    if (score == 0 && !ignoreUADisc) {
      break;
    }
    result=ua.checkAccess(path,userAgent);
    if (result != DISALLOWED || (!ua.isWildcard() || !ignoreUADisc)) {
      break;
    }
  }
  return result;
}
