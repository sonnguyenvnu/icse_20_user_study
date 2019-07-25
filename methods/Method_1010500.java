private void register(String index,SModelReference modelReference){
  if (myModelRef.equals(modelReference)) {
    Logger.getLogger(ImportsHelper.class).warn(String.format("Model %s: no reason to keep imports to self",myModelRef));
  }
  myIndex2Model.put(index,modelReference);
  myModel2Index.put(modelReference,index);
}
