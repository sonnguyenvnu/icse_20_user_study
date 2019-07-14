protected final void doHandle(HttpServletRequest request,HttpServletResponse response,Exception authException) throws IOException, ServletException {
  try {
    ResponseEntity<?> result=exceptionTranslator.translate(authException);
    result=enhanceResponse(result,authException);
    exceptionRenderer.handleHttpEntityResponse(result,new ServletWebRequest(request,response));
    response.flushBuffer();
  }
 catch (  ServletException e) {
    if (handlerExceptionResolver.resolveException(request,response,this,e) == null) {
      throw e;
    }
  }
catch (  IOException e) {
    throw e;
  }
catch (  RuntimeException e) {
    throw e;
  }
catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
