protected void handle(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
  String targetUrl=obtainTarget(request);
  if (!StringUtils.isEmpty(targetUrl)) {
    redirectStrategy.sendRedirect(request,response,targetUrl);
  }
 else {
    RequestDispatcher dispatcher=request.getRequestDispatcher(index);
    dispatcher.forward(request,response);
  }
}
