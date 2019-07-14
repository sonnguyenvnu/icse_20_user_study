private static <T>NodeConverter<T> defaultNodeConverter(){
  return new NodeConverter<T>(){
    @Override public Node toNode(    T object){
      if (object == null) {
        return null;
      }
      StackPane selectedValueContainer=new StackPane();
      selectedValueContainer.getStyleClass().add("combo-box-selected-value-container");
      selectedValueContainer.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
      Label selectedValueLabel=object instanceof Label ? new Label(((Label)object).getText()) : new Label(object.toString());
      selectedValueLabel.setTextFill(Color.BLACK);
      selectedValueContainer.getChildren().add(selectedValueLabel);
      StackPane.setAlignment(selectedValueLabel,Pos.CENTER_LEFT);
      StackPane.setMargin(selectedValueLabel,new Insets(0,0,0,5));
      return selectedValueContainer;
    }
    @SuppressWarnings("unchecked") @Override public T fromNode(    Node node){
      return (T)node;
    }
    @Override public String toString(    T object){
      if (object == null) {
        return null;
      }
      if (object instanceof Label) {
        return ((Label)object).getText();
      }
      return object.toString();
    }
  }
;
}
