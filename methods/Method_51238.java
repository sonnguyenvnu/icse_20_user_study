private String cleanup(String message,Object[] args){
  if (message != null) {
    final String escapedMessage=StringUtils.replace(message,"${","$'{'");
    return MessageFormat.format(escapedMessage,args != null ? args : NO_ARGS);
  }
 else {
    return message;
  }
}
