@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  for (  SModel model : myModels) {
    Element modelXml=new Element(MODEL_TAG);
    modelXml.setAttribute(MODEL_ID,PersistenceFacade.getInstance().asString(model.getReference()));
    element.addContent(modelXml);
  }
}
