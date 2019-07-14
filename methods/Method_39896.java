/** 
 * Returns value of property/attribute. The following value sets are looked up: <ul> <li>request attributes</li> <li>request parameters (multi-part request detected)</li> <li>session attributes</li> <li>context attributes</li> </ul>
 */
public static Object value(final HttpServletRequest request,final String name){
  Object value=request.getAttribute(name);
  if (value != null) {
    return value;
  }
  if (isMultipartRequest(request)) {
    try {
      MultipartRequest multipartRequest=MultipartRequest.getInstance(request);
      value=multipartRequest.getParameter(name);
    }
 catch (    IOException ignore) {
    }
  }
 else {
    String[] params=request.getParameterValues(name);
    if (params != null) {
      if (params.length == 1) {
        value=params[0];
      }
 else {
        value=params;
      }
    }
  }
  if (value != null) {
    return value;
  }
  value=request.getSession().getAttribute(name);
  if (value != null) {
    return value;
  }
  return request.getServletContext().getAttribute(name);
}
