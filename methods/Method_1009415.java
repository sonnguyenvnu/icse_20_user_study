public FXFormNode call(Void aVoid){
  choiceBox.itemsProperty().addListener((observable,oldValue,newValue) -> {
    choiceBox.getSelectionModel().selectFirst();
    ((ObservableList)newValue).addListener(new ListChangeListener(){
      @Override public void onChanged(      Change change){
        change.next();
        if (change.wasAdded()) {
          choiceBox.getSelectionModel().selectFirst();
        }
      }
    }
);
  }
);
  choiceBox.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> {
    if (Objects.nonNull(newValue)) {
      if (!newValue.equals(choiceBox.getItems().get(0))) {
        choiceBox.getItems().removeAll(newValue);
        choiceBox.getItems().add(0,newValue);
        choiceBox.getSelectionModel().selectFirst();
      }
    }
  }
);
  FXFormNodeWrapper fxFormNodeWrapper=new FXFormNodeWrapper(choiceBox,choiceBox.itemsProperty());
  return fxFormNodeWrapper;
}
