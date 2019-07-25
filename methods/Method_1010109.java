@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  element.setAttribute(ATTR1,PersistenceFacade.getInstance().asString(myLanguage));
}
