private HttpInputMessage createHttpInputMessage(NativeWebRequest webRequest) throws Exception {
  HttpServletRequest servletRequest=webRequest.getNativeRequest(HttpServletRequest.class);
  return new ServletServerHttpRequest(servletRequest);
}
