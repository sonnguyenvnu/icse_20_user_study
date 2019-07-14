public Node getContent(){
  return contentContainer.getChildren().size() == 2 ? contentContainer.getChildren().get(1) : null;
}
