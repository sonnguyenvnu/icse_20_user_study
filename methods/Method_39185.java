@Override public void outject(final ActionRequest actionRequest,final Targets targets){
  final HttpServletResponse servletResponse=actionRequest.getHttpServletResponse();
  targets.forEachTargetAndOut(this,(target,out) -> {
    final String value=(String)target.readValue(out);
    if (value != null) {
      servletResponse.setHeader(out.name(),value);
    }
  }
);
}
