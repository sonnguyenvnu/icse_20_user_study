@Override public FXFormNode call(Void param){
  slider.setShowTickLabels(true);
  slider.setShowTickMarks(true);
  HBox hBox=new HBox();
  Label label=new Label();
  hBox.getChildren().addAll(slider,label);
  slider.valueProperty().addListener((observable,oldValue,newValue) -> {
    label.setText(String.format("%1.1f",newValue));
  }
);
  return new FXFormNodeWrapper(hBox,slider.valueProperty()){
  }
;
}
