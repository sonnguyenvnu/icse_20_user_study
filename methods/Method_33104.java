private void clearSelection(JFXListView<?> selectedList){
  if (allowClear) {
    allowClear=false;
    if (this != selectedList) {
      this.getSelectionModel().clearSelection();
    }
    for (int i=0; i < sublistsProperty.get().size(); i++) {
      if (sublistsProperty.get().get(i) != selectedList) {
        sublistsProperty.get().get(i).getSelectionModel().clearSelection();
      }
    }
    allowClear=true;
  }
}
