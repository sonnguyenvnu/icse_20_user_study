@Override public void read(Element element,Project project) throws CantLoadSomethingException {
  Element treeWrapperXML=element.getChild(TREE_WRAPPER);
  myTreeComponent.read(treeWrapperXML,project);
}
