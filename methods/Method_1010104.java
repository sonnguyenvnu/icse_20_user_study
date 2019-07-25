@Override public void read(Element element,Project project) throws CantLoadSomethingException {
  for (  Element childXML : element.getChildren(CHILD)) {
    try {
      BaseNode child=(BaseNode)Class.forName(childXML.getAttributeValue(CHILD_CLASS)).newInstance();
      child.read(childXML,project);
      myChildren.add(child);
      child.setParent(this);
    }
 catch (    Throwable t) {
      throw new CantLoadSomethingException("Error while instantiating node: " + t.getMessage(),t);
    }
  }
}
