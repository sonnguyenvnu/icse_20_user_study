/** 
 * add url to fetch
 * @param requestString requestString
 */
public void addTargetRequest(String requestString){
  if (StringUtils.isBlank(requestString) || requestString.equals("#")) {
    return;
  }
  requestString=UrlUtils.canonicalizeUrl(requestString,url.toString());
  targetRequests.add(new Request(requestString));
}
