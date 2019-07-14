@Override protected void unsuccessfulAuthentication(HttpServletRequest request,HttpServletResponse response,AuthenticationException failed) throws IOException, ServletException {
  if (failed instanceof AccessTokenRequiredException) {
    throw failed;
  }
 else {
    super.unsuccessfulAuthentication(request,response,failed);
  }
}
