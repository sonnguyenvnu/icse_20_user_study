@Override public void inject(final ActionRequest actionRequest,final Targets targets){
  final HttpServletRequest servletRequest=actionRequest.getHttpServletRequest();
  final HttpSession session=servletRequest.getSession();
  final Enumeration<String> attributeNames=session.getAttributeNames();
  while (attributeNames.hasMoreElements()) {
    final String attrName=attributeNames.nextElement();
    targets.forEachTargetAndIn(this,(target,in) -> {
      final String name=in.matchedName(attrName);
      if (name != null) {
        final Object attrValue=session.getAttribute(attrName);
        target.writeValue(name,attrValue,true);
      }
    }
);
  }
}
