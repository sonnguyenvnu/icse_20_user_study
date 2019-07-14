/** 
 * Inject request parameters.
 */
protected void injectParameters(final HttpServletRequest servletRequest,final Targets targets){
  final boolean encode=encodeGetParams && servletRequest.getMethod().equals("GET");
  final Enumeration<String> paramNames=servletRequest.getParameterNames();
  while (paramNames.hasMoreElements()) {
    final String paramName=paramNames.nextElement();
    if (servletRequest.getAttribute(paramName) != null) {
      continue;
    }
    targets.forEachTargetAndIn(this,(target,in) -> {
      final String name=in.matchedName(paramName);
      if (name != null) {
        String[] paramValues=servletRequest.getParameterValues(paramName);
        paramValues=ServletUtil.prepareParameters(paramValues,treatEmptyParamsAsNull,ignoreEmptyRequestParams);
        if (paramValues != null) {
          if (encode) {
            for (int j=0; j < paramValues.length; j++) {
              final String p=paramValues[j];
              if (p != null) {
                final String encoding=madvocEncoding.getEncoding();
                paramValues[j]=StringUtil.convertCharset(p,StringPool.ISO_8859_1,encoding);
              }
            }
          }
          final Object value=(paramValues.length != 1 ? paramValues : paramValues[0]);
          target.writeValue(name,value,true);
        }
      }
    }
);
  }
}
