@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  super.write(element,project);
  element.setAttribute("l",PersistenceFacade.getInstance().asString(myLanguage));
}
