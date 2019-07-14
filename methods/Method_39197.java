@Override public void outject(final ActionRequest actionRequest,final Targets targets){
  final ServletContext context=actionRequest.getHttpServletRequest().getServletContext();
  targets.forEachTargetAndOut(this,(target,out) -> {
    final Object value=target.readValue(out);
    context.setAttribute(out.name(),value);
  }
);
}
