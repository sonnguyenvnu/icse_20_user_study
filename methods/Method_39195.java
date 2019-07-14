/** 
 * Inject uploaded files from multipart request parameters.
 */
protected void injectUploadedFiles(final HttpServletRequest servletRequest,final Targets targets){
  if (!(servletRequest instanceof MultipartRequestWrapper)) {
    return;
  }
  final MultipartRequestWrapper multipartRequest=(MultipartRequestWrapper)servletRequest;
  if (!multipartRequest.isMultipart()) {
    return;
  }
  final Enumeration<String> paramNames=multipartRequest.getFileParameterNames();
  while (paramNames.hasMoreElements()) {
    final String paramName=paramNames.nextElement();
    if (servletRequest.getAttribute(paramName) != null) {
      continue;
    }
    targets.forEachTargetAndIn(this,(target,in) -> {
      final String name=in.matchedName(paramName);
      if (name != null) {
        final FileUpload[] paramValues=multipartRequest.getFiles(paramName);
        if (ignoreInvalidUploadFiles) {
          for (int j=0; j < paramValues.length; j++) {
            final FileUpload paramValue=paramValues[j];
            if ((!paramValue.isValid()) || (!paramValue.isUploaded())) {
              paramValues[j]=null;
            }
          }
        }
        final Object value=(paramValues.length == 1 ? paramValues[0] : paramValues);
        target.writeValue(name,value,true);
      }
    }
);
  }
}
