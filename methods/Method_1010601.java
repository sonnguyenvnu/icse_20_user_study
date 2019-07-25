public void attach(SModel model,boolean doCheckImmediately){
  assert myModel == null;
  myModel=model;
  myModel.addChangeListener(myNodeListener);
  myModel.addModelListener(myModelListener);
  if (doCheckImmediately) {
    addRefsFromModel(model);
  }
}
