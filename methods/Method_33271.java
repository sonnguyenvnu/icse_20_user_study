private void createPromptNode(){
  if (promptText != null || !linesWrapper.usePromptText.get()) {
    return;
  }
  promptText=new Text();
  StackPane.setAlignment(promptText,Pos.CENTER_LEFT);
  promptText.textProperty().bind(getSkinnable().promptTextProperty());
  promptText.fillProperty().bind(linesWrapper.animatedPromptTextFill);
  promptText.getStyleClass().addAll("text");
  promptText.getTransforms().add(linesWrapper.promptTextScale);
  promptText.visibleProperty().bind(linesWrapper.usePromptText);
  promptText.setTranslateX(1);
  linesWrapper.promptContainer.getChildren().add(promptText);
  if (getSkinnable().isFocused() && ((JFXComboBox<T>)getSkinnable()).isLabelFloat()) {
    promptText.setTranslateY(-snapPosition(promptText.getBaselineOffset() + promptText.getLayoutBounds().getHeight() * .36));
    linesWrapper.promptTextScale.setX(0.85);
    linesWrapper.promptTextScale.setY(0.85);
  }
}
