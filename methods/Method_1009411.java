@Override public FXFormNode call(Void param){
  textField.setPromptText(promptText);
  tooltip.setText(promptText);
  textField.setDisable(false);
  changeButton.setText("Change");
  browseButton.setText("Browse");
  textField.textProperty().bindBidirectional(property);
  property.addListener((observable,oldValue,newValue) -> {
    if (Objects.nonNull(newValue)) {
      tooltip.setText(newValue);
      if (newValue.isEmpty()) {
        property.set(null);
      }
    }
  }
);
  browseButton.visibleProperty().bind(property.isNotNull());
  browseButton.managedProperty().bind(property.isNotNull());
  textField.setOnMouseEntered(event -> {
    Optional.of(textField).filter(e -> !e.isFocused()).map(TextField::getText).ifPresent(text -> textField.positionCaret(text.length()));
  }
);
  textField.setOnMouseExited(event -> {
    if (!textField.isFocused())     textField.positionCaret(0);
  }
);
  changeButton.setOnAction(this::chooser);
  HBox hBox=new HBox(5);
  hBox.getChildren().addAll(textField,changeButton,browseButton);
  HBox.setHgrow(textField,Priority.ALWAYS);
  Tooltip.install(textField,tooltip);
  return new FXFormNodeWrapper(hBox,property);
}
