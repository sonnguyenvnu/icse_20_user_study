public void dumpConfiguration(){
  Logger log=Logger.getLogger(ConfigurationBase.class);
  if (debug) {
    Field[] fields=ConfigurationBase.class.getDeclaredFields();
    for (    Field field : fields) {
      try {
        Object value=field.get(this);
        String strValue=String.valueOf(value);
        if (value != null && field.getName().matches("oAuthConsumerSecret|oAuthAccessTokenSecret|password")) {
          strValue=String.valueOf(value).replaceAll(".","*");
        }
        log.debug(field.getName() + ": " + strValue);
      }
 catch (      IllegalAccessException ignore) {
      }
    }
  }
}
