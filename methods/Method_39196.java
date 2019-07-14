@Override public void outject(final ActionRequest actionRequest,final Targets targets){
  final HttpServletRequest servletRequest=actionRequest.getHttpServletRequest();
  targets.forEachTargetAndOut(this,(target,out) -> {
    final Object value=target.readValue(out);
    servletRequest.setAttribute(out.name(),value);
  }
);
}
