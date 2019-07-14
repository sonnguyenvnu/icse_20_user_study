/** 
 * Injects request attributes.
 */
protected void injectAttributes(final HttpServletRequest servletRequest,final Targets targets){
  final Enumeration<String> attributeNames=servletRequest.getAttributeNames();
  while (attributeNames.hasMoreElements()) {
    final String attrName=attributeNames.nextElement();
    targets.forEachTargetAndIn(this,(target,in) -> {
      final String name=in.matchedName(attrName);
      if (name != null) {
        final Object attrValue=servletRequest.getAttribute(attrName);
        target.writeValue(name,attrValue,true);
      }
    }
);
  }
}
