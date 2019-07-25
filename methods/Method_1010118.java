public static FindUsagesScope load(Element element,Project project) throws CantLoadSomethingException {
  Element scopeXml=element.getChild(SCOPE_TAG);
  String scopeClass=scopeXml.getAttributeValue(SCOPE_CLASS_ATTR);
  try {
    return (FindUsagesScope)Class.forName(scopeClass).getConstructor(Element.class,Project.class).newInstance(scopeXml,project);
  }
 catch (  Exception e) {
    throw new CantLoadSomethingException(e);
  }
}
