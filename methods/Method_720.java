private String getJsonpParameterValue(HttpServletRequest request){
  if (this.jsonpParameterNames != null) {
    for (    String name : this.jsonpParameterNames) {
      String value=request.getParameter(name);
      if (IOUtils.isValidJsonpQueryParam(value)) {
        return value;
      }
      if (logger.isDebugEnabled()) {
        logger.debug("Ignoring invalid jsonp parameter value: " + value);
      }
    }
  }
  return null;
}
