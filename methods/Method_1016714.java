void reset(){
  httpMethodBox.setValue(HTTPConstants.GET);
  addressField.clear();
  headerTabController.clear();
  clearParams();
  bodyTabController.reset();
  responseArea.clear();
  showLayer(ResponseLayer.PROMPT);
  responseTabPane.getSelectionModel().select(0);
}
