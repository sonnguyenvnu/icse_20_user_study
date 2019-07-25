@Override public FXFormNode call(Void param){
  return new FXFormNodeWrapper(spinner,spinner.getValueFactory().valueProperty());
}
