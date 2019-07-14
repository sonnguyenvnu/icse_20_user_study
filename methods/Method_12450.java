private String safeFormat(String format,Object... args){
  try {
    return String.format(format,args);
  }
 catch (  MissingFormatArgumentException e) {
    LOGGER.warn("Exception while trying to format the message. Falling back by using the format string.",e);
    return format;
  }
}
