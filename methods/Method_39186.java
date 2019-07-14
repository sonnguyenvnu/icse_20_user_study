public void inject(final ActionRequest actionRequest,final Targets targets){
  final HttpServletRequest servletRequest=actionRequest.getHttpServletRequest();
  final HttpServletResponse servletResponse=actionRequest.getHttpServletResponse();
  targets.forEachTargetAndIn(madvocScope,(target,in) -> {
    final Class inType=in.type();
    Object value=null;
    if (inType == HttpServletRequest.class) {
      value=servletRequest;
    }
 else     if (inType == ServletRequest.class) {
      value=servletRequest;
    }
 else     if (inType == HttpServletResponse.class) {
      value=servletResponse;
    }
 else     if (inType == ServletResponse.class) {
      value=servletResponse;
    }
 else     if (inType == HttpSession.class) {
      value=servletRequest.getSession();
    }
 else     if (inType == ServletContext.class) {
      value=servletRequest.getServletContext();
    }
 else     if (inType == AsyncContext.class) {
      value=servletRequest.getAsyncContext();
    }
 else     if (inType == ActionRequest.class) {
      value=actionRequest;
    }
    if (value != null) {
      target.writeValue(in,value,true);
    }
  }
);
}
