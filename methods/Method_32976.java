private void createPromptNode(){
  if (promptText != null || !linesWrapper.usePromptText.get()) {
    return;
  }
  promptText=new Text();
  promptText.setManaged(false);
  promptText.getStyleClass().add("text");
  promptText.visibleProperty().bind(linesWrapper.usePromptText);
  promptText.fontProperty().bind(getSkinnable().fontProperty());
  promptText.textProperty().bind(getSkinnable().promptTextProperty());
  promptText.fillProperty().bind(linesWrapper.animatedPromptTextFill);
  promptText.setLayoutX(1);
  promptText.setTranslateX(1);
  promptText.getTransforms().add(linesWrapper.promptTextScale);
  linesWrapper.promptContainer.getChildren().add(promptText);
  if (getSkinnable().isFocused() && ((JFXTextArea)getSkinnable()).isLabelFloat()) {
    promptText.setTranslateY(-Math.floor(scrollPane.getHeight()));
    linesWrapper.promptTextScale.setX(0.85);
    linesWrapper.promptTextScale.setY(0.85);
  }
  try {
    reflectionFieldConsumer("promptNode",field -> {
      Object oldValue=field.get(this);
      if (oldValue != null) {
        removeHighlight(Arrays.asList(((Node)oldValue)));
      }
      field.set(this,promptText);
    }
);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
