private void addPostParameter(char[] supportedParams,char paramKey,List<HttpParameter> pagingParams,String paramName,long paramValue){
  boolean supported=false;
  for (  char supportedParam : supportedParams) {
    if (supportedParam == paramKey) {
      supported=true;
      break;
    }
  }
  if (!supported && -1 != paramValue) {
    throw new IllegalStateException("Paging parameter [" + paramName + "] is not supported with this operation.");
  }
  if (-1 != paramValue) {
    pagingParams.add(new HttpParameter(paramName,String.valueOf(paramValue)));
  }
}
