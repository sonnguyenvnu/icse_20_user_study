/** 
 * @param properties the properties to set
 */
public void setProperties(Properties properties){
  this.properties=properties;
  if (null != this.properties.getProperty("driver")) {
    this.driverClass=this.properties.getProperty("driver");
  }
  if (null != this.properties.getProperty("characterset")) {
    this.characterSet=this.properties.getProperty("characterset");
  }
  if (null != this.properties.getProperty("sourcecodetypes")) {
    this.sourceCodeTypes=this.properties.getProperty("sourcecodetypes");
  }
  if (null != this.properties.getProperty("languages")) {
    this.languages=this.properties.getProperty("languages");
  }
  if (null != this.properties.getProperty("returnType")) {
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("returnType" + this.properties.getProperty("returnType"));
    }
    this.sourceCodeReturnType=Integer.parseInt(this.properties.getProperty("returnType"));
  }
}
