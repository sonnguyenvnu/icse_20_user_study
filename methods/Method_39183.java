@Override public void outject(final ActionRequest actionRequest,final Targets targets){
  final HttpServletResponse servletResponse=actionRequest.getHttpServletResponse();
  targets.forEachTargetAndOut(this,(target,out) -> {
    final Cookie cookie=(Cookie)target.readValue(out);
    if (cookie != null) {
      servletResponse.addCookie(cookie);
    }
  }
);
}
