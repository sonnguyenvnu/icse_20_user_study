private void maybeThrowExceptionFromHeader(String authenticateHeader,String headerType){
  headerType=headerType.toLowerCase();
  if (authenticateHeader.toLowerCase().startsWith(headerType)) {
    Map<String,String> headerEntries=StringSplitUtils.splitEachArrayElementAndCreateMap(StringSplitUtils.splitIgnoringQuotes(authenticateHeader.substring(headerType.length()),','),"=","\"");
    OAuth2Exception ex=OAuth2Exception.valueOf(headerEntries);
    if (ex instanceof InvalidTokenException) {
      throw new AccessTokenRequiredException(resource);
    }
    throw ex;
  }
}
