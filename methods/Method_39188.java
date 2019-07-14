public boolean isUnusedType(final Class inType){
  if (inType == HttpServletRequest.class) {
    return false;
  }
 else   if (inType == ServletRequest.class) {
    return false;
  }
 else   if (inType == HttpServletResponse.class) {
    return false;
  }
 else   if (inType == ServletResponse.class) {
    return false;
  }
 else   if (inType == HttpSession.class) {
    return false;
  }
 else   if (inType == ServletContext.class) {
    return false;
  }
 else   if (inType == AsyncContext.class) {
    return false;
  }
 else   if (inType == ActionRequest.class) {
    return false;
  }
  return false;
}
