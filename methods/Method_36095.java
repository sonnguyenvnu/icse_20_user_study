@Override protected void service(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws ServletException, IOException {
  LocalNotifier.set(notifier);
  Request request=new WireMockHttpServletRequestAdapter(httpServletRequest,multipartRequestConfigurer,mappedUnder);
  ServletHttpResponder responder=new ServletHttpResponder(httpServletRequest,httpServletResponse);
  requestHandler.handle(request,responder);
}
