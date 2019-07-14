public static Response unwrapResponse(HttpServletResponse httpServletResponse){
  if (httpServletResponse instanceof HttpServletResponseWrapper) {
    ServletResponse unwrapped=((HttpServletResponseWrapper)httpServletResponse).getResponse();
    return (Response)unwrapped;
  }
  return (Response)httpServletResponse;
}
