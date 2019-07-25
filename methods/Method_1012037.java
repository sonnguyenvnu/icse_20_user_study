@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  Element treeWrapperXML=new Element(TREE_WRAPPER);
  myTreeComponent.write(treeWrapperXML,project);
  element.addContent(treeWrapperXML);
}
