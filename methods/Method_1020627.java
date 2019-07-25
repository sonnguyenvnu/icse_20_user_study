@Override public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException authException) throws IOException, ServletException {
  response.setHeader("Content-Type","application/json;charset=utf-8");
  response.getWriter().print("{\"code\":1,\"message\":\"" + authException.getMessage() + "\"}");
  response.getWriter().flush();
}
