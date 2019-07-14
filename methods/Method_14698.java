static public Properties parseUrlParameters(HttpServletRequest request){
  Properties options=new Properties();
  String query=request.getQueryString();
  if (query != null) {
    if (query.startsWith("?")) {
      query=query.substring(1);
    }
    parseParameters(options,query);
  }
  return options;
}
