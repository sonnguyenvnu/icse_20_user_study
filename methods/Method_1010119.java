public static void save(FindUsagesScope scope,Element element,Project project) throws CantSaveSomethingException {
  Element scopeXml=new Element(SCOPE_TAG);
  scopeXml.setAttribute(SCOPE_CLASS_ATTR,scope.getClass().getName());
  scope.write(scopeXml,project);
  element.addContent(scopeXml);
}
