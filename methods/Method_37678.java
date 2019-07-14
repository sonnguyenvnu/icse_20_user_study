public static String encodeQuery(final String query){
  return encodeUriComponent(query,JoddCore.encoding,URIPart.QUERY);
}
