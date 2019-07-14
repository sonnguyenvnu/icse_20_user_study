public void setGraphic(Node node){
  if (graphic != null) {
    graphicContainer.getChildren().remove(graphic);
  }
  if (node != null) {
    graphicContainer.getChildren().add(0,node);
  }
  graphic=node;
}
