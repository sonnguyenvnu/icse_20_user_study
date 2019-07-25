@Override public void read(Element element,Project project) throws CantLoadSomethingException {
  myCaption=element.getAttributeValue(CAPTION);
  myAdditionalInfo=element.getAttributeValue(INFO);
  myIsExcluded=Boolean.parseBoolean(element.getAttributeValue(EXCLUDED));
  myIsPathTail=Boolean.parseBoolean(element.getAttributeValue(ISRESULT));
  myResultsSection=Boolean.parseBoolean(element.getAttributeValue(RESULTS_SECTION));
  Element roleXML=element.getChild(ROLE);
  myRole=PathItemRole.read(roleXML);
  for (  Element childElement : element.getChildren("child")) {
    final String nodeClassName=childElement.getChild("instance").getAttributeValue("qcn");
    try {
      Class<?> cls=Class.forName(nodeClassName);
      BaseNodeData ch=(BaseNodeData)cls.getConstructor(Element.class,Project.class).newInstance(childElement,project);
      addChild(ch);
    }
 catch (    ClassNotFoundException|InstantiationException|IllegalAccessException|NoSuchMethodException|InvocationTargetException e) {
      if (e instanceof InvocationTargetException && e.getCause() instanceof CantLoadSomethingException) {
        throw (CantLoadSomethingException)e.getCause();
      }
      throw new CantLoadSomethingException("can't instantiate node " + nodeClassName,e);
    }
  }
}
