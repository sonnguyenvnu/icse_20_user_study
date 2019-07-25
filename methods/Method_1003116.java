private static String translate(String key,String... params){
  String message=null;
  if (MESSAGES != null) {
    message=MESSAGES.getProperty(key);
  }
  if (message == null) {
    message="(Message " + key + " not found)";
  }
  if (params != null) {
    for (int i=0; i < params.length; i++) {
      String s=params[i];
      if (s != null && s.length() > 0) {
        params[i]=StringUtils.quoteIdentifier(s);
      }
    }
    message=MessageFormat.format(message,(Object[])params);
  }
  return message;
}
