private void populate(){
  container.getChildren().clear();
  map.forEach((key,value) -> {
    Label keyLabel=new Label(key + ": ");
    keyLabel.getStyleClass().addAll(keyLabelStyleClass,responseHeaderLabel);
    Label valueLabel=new Label(value);
    valueLabel.getStyleClass().addAll(valueLabelStyleClass,responseHeaderLabel);
    container.getChildren().add(new HBox(keyLabel,valueLabel));
  }
);
}
