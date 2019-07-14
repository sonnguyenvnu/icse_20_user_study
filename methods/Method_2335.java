@Override public void setProperties(Properties properties){
  super.setProperties(properties);
  this.addGWTInterface=Boolean.valueOf(properties.getProperty("addGWTInterface")).booleanValue();
  this.suppressJavaInterface=Boolean.valueOf(properties.getProperty("suppressJavaInterface")).booleanValue();
}
