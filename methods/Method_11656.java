private Object convert(String value,ObjectFormatter objectFormatter){
  try {
    Object format=objectFormatter.format(value);
    logger.debug("String {} is converted to {}",value,format);
    return format;
  }
 catch (  Exception e) {
    logger.error("convert " + value + " to " + objectFormatter.clazz() + " error!",e);
  }
  return null;
}
