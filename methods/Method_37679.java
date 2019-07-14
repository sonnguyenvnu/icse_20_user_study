public static String encodeQueryParam(final String queryParam){
  return encodeUriComponent(queryParam,JoddCore.encoding,URIPart.QUERY_PARAM);
}
