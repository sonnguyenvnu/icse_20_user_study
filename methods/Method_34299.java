private HttpOutputMessage createHttpOutputMessage(NativeWebRequest webRequest) throws Exception {
  HttpServletResponse servletResponse=(HttpServletResponse)webRequest.getNativeResponse();
  return new ServletServerHttpResponse(servletResponse);
}
