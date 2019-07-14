@Override public void inject(final ActionRequest actionRequest,final Targets targets){
  final HttpServletRequest servletRequest=actionRequest.getHttpServletRequest();
  targets.forEachTargetAndIn(this,(target,in) -> {
    final Enumeration<String> headerNames=servletRequest.getHeaders(in.name());
    if (headerNames != null) {
      final List<String> allValues=new ArrayList<>();
      while (headerNames.hasMoreElements()) {
        allValues.add(headerNames.nextElement());
      }
      final Object value;
      if (allValues.size() == 1) {
        value=allValues.get(0);
      }
 else {
        value=allValues;
      }
      target.writeValue(in,value,true);
    }
  }
);
}
