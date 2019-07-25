private void unsuccess(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
  SecurityContextHolder.clearContext();
  if (logger.isDebugEnabled()) {
    logger.debug("Authentication request failed");
    logger.debug("Updated SecurityContextHolder to contain null Authentication");
  }
  unSuccessfullAuthentication(request,response);
}
