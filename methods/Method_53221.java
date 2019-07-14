/** 
 * Converts the pagination parameters into a List of PostParameter.<br> This method also Validates the preset parameters, and throws IllegalStateException if any unsupported parameter is set.
 * @param supportedParams  char array representation of supported parameters
 * @param perPageParamName name used for per-page parameter. getUserListStatuses() requires "per_page" instead of "count".
 * @return list of PostParameter
 */
List<HttpParameter> asPostParameterList(char[] supportedParams,String perPageParamName){
  java.util.List<HttpParameter> pagingParams=new ArrayList<HttpParameter>(supportedParams.length);
  addPostParameter(supportedParams,'s',pagingParams,"since_id",getSinceId());
  addPostParameter(supportedParams,'m',pagingParams,"max_id",getMaxId());
  addPostParameter(supportedParams,'c',pagingParams,perPageParamName,getCount());
  addPostParameter(supportedParams,'p',pagingParams,"page",getPage());
  if (pagingParams.size() == 0) {
    return NULL_PARAMETER_LIST;
  }
 else {
    return pagingParams;
  }
}
