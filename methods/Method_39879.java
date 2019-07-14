/** 
 * Determine if request is eligible for GZipping.
 */
protected boolean isGzipEligible(final HttpServletRequest request){
  if (requestParameterName.length() != 0) {
    String forceGzipString=request.getParameter(requestParameterName);
    if (forceGzipString != null) {
      return Converter.get().toBooleanValue(forceGzipString,false);
    }
  }
  String uri=request.getRequestURI();
  if (uri == null) {
    return false;
  }
  uri=uri.toLowerCase();
  boolean result=false;
  if (matches == null) {
    if (extensions == null) {
      return true;
    }
    String extension=FileNameUtil.getExtension(uri);
    if (extension.length() > 0) {
      extension=extension.toLowerCase();
      if (StringUtil.equalsOne(extension,extensions) != -1) {
        result=true;
      }
    }
  }
 else {
    if (wildcards) {
      result=Wildcard.matchPathOne(uri,matches) != -1;
    }
 else {
      for (      String match : matches) {
        if (uri.contains(match)) {
          result=true;
          break;
        }
      }
    }
  }
  if ((result) && (excludes != null)) {
    if (wildcards) {
      if (Wildcard.matchPathOne(uri,excludes) != -1) {
        result=false;
      }
    }
 else {
      for (      String exclude : excludes) {
        if (uri.contains(exclude)) {
          result=false;
          break;
        }
      }
    }
  }
  return result;
}
