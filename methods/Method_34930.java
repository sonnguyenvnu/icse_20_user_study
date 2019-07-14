/** 
 * Match the current user agent directive set with the given user agent. The returned value will be the maximum match length of any user agent.
 * @param userAgent The user agent used by the crawler
 * @return The maximum length of a matching user agent in this set of directives
 */
public int match(String userAgent){
  userAgent=userAgent.toLowerCase();
  int maxLength=0;
  for (  String ua : userAgents) {
    if (ua.equals("*") || userAgent.contains(ua)) {
      maxLength=Math.max(maxLength,ua.length());
    }
  }
  return maxLength;
}
