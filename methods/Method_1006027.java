@Override public Node description(){
  VBox container=new VBox();
  Label header=new Label(Localization.lang("Added string"));
  header.getStyleClass().add("sectionHeader");
  container.getChildren().addAll(header,new Label(Localization.lang("Label") + ": " + string.getName()),new Label(Localization.lang("Content") + ": " + string.getContent()));
  return container;
}
