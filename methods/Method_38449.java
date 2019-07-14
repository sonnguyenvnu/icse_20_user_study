/** 
 * Reads filter config parameters and set into destination target.
 */
protected void readFilterConfigParameters(final FilterConfig filterConfig,final Object target,final String... parameters){
  for (  String parameter : parameters) {
    String value=filterConfig.getInitParameter(parameter);
    if (value != null) {
      BeanUtil.declared.setProperty(target,parameter,value);
    }
  }
}
