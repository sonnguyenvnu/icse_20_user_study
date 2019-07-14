@Override public void outject(final ActionRequest actionRequest,final Targets targets){
  final HttpServletRequest servletRequest=actionRequest.getHttpServletRequest();
  final HttpSession session=servletRequest.getSession();
  targets.forEachTargetAndOut(this,(target,out) -> {
    final Object value=target.readValue(out);
    session.setAttribute(out.name(),value);
  }
);
}
