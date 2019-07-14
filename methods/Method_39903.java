/** 
 * Copies all request parameters to attributes.
 */
public static void copyParamsToAttributes(final HttpServletRequest servletRequest,final boolean treatEmptyParamsAsNull,final boolean ignoreEmptyRequestParams){
  Enumeration paramNames=servletRequest.getParameterNames();
  while (paramNames.hasMoreElements()) {
    String paramName=(String)paramNames.nextElement();
    if (servletRequest.getAttribute(paramName) != null) {
      continue;
    }
    String[] paramValues=servletRequest.getParameterValues(paramName);
    paramValues=prepareParameters(paramValues,treatEmptyParamsAsNull,ignoreEmptyRequestParams);
    if (paramValues == null) {
      continue;
    }
    servletRequest.setAttribute(paramName,paramValues.length == 1 ? paramValues[0] : paramValues);
  }
  if (!(servletRequest instanceof MultipartRequestWrapper)) {
    return;
  }
  MultipartRequestWrapper multipartRequest=(MultipartRequestWrapper)servletRequest;
  if (!multipartRequest.isMultipart()) {
    return;
  }
  paramNames=multipartRequest.getFileParameterNames();
  while (paramNames.hasMoreElements()) {
    String paramName=(String)paramNames.nextElement();
    if (servletRequest.getAttribute(paramName) != null) {
      continue;
    }
    FileUpload[] paramValues=multipartRequest.getFiles(paramName);
    servletRequest.setAttribute(paramName,paramValues.length == 1 ? paramValues[0] : paramValues);
  }
}
