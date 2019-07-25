@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  element.setAttribute(UID,PersistenceFacade.getInstance().asString(myReference));
}
