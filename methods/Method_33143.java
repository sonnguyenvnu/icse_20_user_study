public void setContent(Node content){
  if (contentContainer.getChildren().size() == 2) {
    contentContainer.getChildren().set(1,content);
  }
 else   if (contentContainer.getChildren().size() == 1) {
    contentContainer.getChildren().add(content);
  }
 else {
    contentContainer.getChildren().setAll(headerSpace,content);
  }
  VBox.setVgrow(content,Priority.ALWAYS);
}
