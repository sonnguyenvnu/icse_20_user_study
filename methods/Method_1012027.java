@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  super.write(element,project);
  element.setAttribute(NODE,PersistenceFacade.getInstance().asString(myNodePointer));
  if (myIsRootNode) {
    element.setAttribute(IS_ROOT,Boolean.TRUE.toString());
  }
}
