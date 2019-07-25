private void success(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws AuthenticationException {
  SecurityContextHolder.getContext().setAuthentication(authentication);
  if (logger.isDebugEnabled()) {
    logger.debug("Authentication success");
    logger.debug("Updated SecurityContextHolder to containAuthentication");
  }
  successfullAuthentication(request,response,authentication);
}
