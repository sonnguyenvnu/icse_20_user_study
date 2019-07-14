@SuppressWarnings("rawtypes") public void setConnectProperties(Properties properties){
  if (properties == null) {
    properties=new Properties();
  }
  boolean equals;
  if (properties.size() == this.connectProperties.size()) {
    equals=true;
    for (    Map.Entry entry : properties.entrySet()) {
      Object value=this.connectProperties.get(entry.getKey());
      Object entryValue=entry.getValue();
      if (value == null && entryValue != null) {
        equals=false;
        break;
      }
      if (!value.equals(entry.getValue())) {
        equals=false;
        break;
      }
    }
  }
 else {
    equals=false;
  }
  if (!equals) {
    if (inited && LOG.isInfoEnabled()) {
      LOG.info("connectProperties changed : " + this.connectProperties + " -> " + properties);
    }
    configFromPropety(properties);
    for (    Filter filter : this.filters) {
      filter.configFromProperties(properties);
    }
    if (exceptionSorter != null) {
      exceptionSorter.configFromProperties(properties);
    }
    if (validConnectionChecker != null) {
      validConnectionChecker.configFromProperties(properties);
    }
    if (statLogger != null) {
      statLogger.configFromProperties(properties);
    }
  }
  this.connectProperties=properties;
}
