@Override public Node description(){
  VBox container=new VBox();
  Label header=new Label(name);
  header.getStyleClass().add("sectionHeader");
  container.getChildren().add(header);
  for (  FieldChangeViewModel change : fieldChanges) {
    container.getChildren().add(change.description());
  }
  return container;
}
