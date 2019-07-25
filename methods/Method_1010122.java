@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  for (  SModule module : myModules) {
    Element moduleXml=new Element(MODULE_TAG);
    moduleXml.setAttribute(MODULE_ID,PersistenceFacade.getInstance().asString(module.getModuleReference()));
    element.addContent(moduleXml);
  }
}
