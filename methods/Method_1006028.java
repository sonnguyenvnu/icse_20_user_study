@Override public Node description(){
  VBox container=new VBox();
  Label header=new Label(Localization.lang("Modified string"));
  header.getStyleClass().add("sectionHeader");
  container.getChildren().addAll(header,new Label(Localization.lang("Label") + ": " + label),new Label(Localization.lang("Content") + ": " + disk));
  if (string == null) {
    container.getChildren().add(new Label(Localization.lang("Cannot merge this change") + ": " + Localization.lang("The string has been removed locally")));
  }
 else {
    container.getChildren().add(new Label(Localization.lang("Current content") + ": " + string.getContent()));
  }
  return container;
}
