/** 
 * Gets a parameter specified by the given name from request body form or query string.
 * @param name the given name
 * @return parameter, returns {@code null} if not found
 */
public String param(final String name){
  try {
    return request.getParameter(name);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Can't parse request parameter [uri=" + request.getRequestURI() + ", method=" + request.getMethod() + ", parameterName=" + name + "]: " + e.getMessage());
    return null;
  }
}
