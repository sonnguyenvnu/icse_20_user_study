/** 
 * Checks if some parameter is in GET parameters.
 */
public boolean isGetParameter(final HttpServletRequest request,String name){
  name=URLCoder.encodeQueryParam(name) + '=';
  String query=request.getQueryString();
  String[] nameValuePairs=StringUtil.splitc(query,'&');
  for (  String nameValuePair : nameValuePairs) {
    if (nameValuePair.startsWith(name)) {
      return true;
    }
  }
  return false;
}
