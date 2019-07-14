@Override public void onLogoutSuccess(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Authentication authentication) throws IOException, ServletException {
  httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
  httpServletResponse.setContentType("application/json;charset=utf-8");
  httpServletResponse.getWriter().write("??????????");
}
