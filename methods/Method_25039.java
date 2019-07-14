private String calculateAllowHeaders(Map<String,String> queryHeaders){
  return System.getProperty(ACCESS_CONTROL_ALLOW_HEADER_PROPERTY_NAME,DEFAULT_ALLOWED_HEADERS);
}
