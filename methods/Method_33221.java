private void selectItem(){
  T item=suggestionList.getSelectionModel().getSelectedItem();
  if (item == null) {
    try {
      suggestionList.getSelectionModel().select(0);
      item=suggestionList.getSelectionModel().getSelectedItem();
    }
 catch (    Exception e) {
    }
  }
  if (item != null) {
    control.getSelectionHandler().handle(new JFXAutoCompleteEvent<T>(JFXAutoCompleteEvent.SELECTION,item));
  }
}
