@Override public Node description(){
  VBox container=new VBox();
  Label header=new Label(Localization.lang("Changed preamble"));
  header.getStyleClass().add("sectionHeader");
  container.getChildren().add(header);
  if (StringUtil.isNotBlank(change.getOriginalPreamble())) {
    container.getChildren().add(new Label(Localization.lang("Current value") + ": " + change.getOriginalPreamble()));
  }
  if (StringUtil.isNotBlank(change.getNewPreamble())) {
    container.getChildren().add(new Label(Localization.lang("Value set externally") + ": " + change.getNewPreamble()));
  }
 else {
    container.getChildren().add(new Label(Localization.lang("Value cleared externally")));
  }
  return container;
}
