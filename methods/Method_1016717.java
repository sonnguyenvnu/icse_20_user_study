void clear(){
  if (headers != null)   headers.clear();
  if (controllers != null)   controllers.clear();
  headersBox.getChildren().clear();
  controllersCount.set(0);
  addHeader();
}
