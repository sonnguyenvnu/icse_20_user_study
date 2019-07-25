@Override public void read(Element element,Project project) throws CantLoadSomethingException {
  final String lang=element.getAttributeValue(ATTR1);
  if (lang == null) {
    throw new CantLoadSomethingException();
  }
  try {
    myLanguage=PersistenceFacade.getInstance().createLanguage(lang);
  }
 catch (  FormatException ex) {
    throw new CantLoadSomethingException(ex);
  }
}
