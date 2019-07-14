@Deprecated void addSublist(JFXListView<?> subList,int index){
  if (!sublistsProperty.get().contains(subList)) {
    sublistsProperty.get().add(subList);
    sublistsIndices.put(index,subList);
    subList.getSelectionModel().selectedIndexProperty().addListener((o,oldVal,newVal) -> {
      if (newVal.intValue() != -1) {
        updateOverAllSelectedIndex();
      }
    }
);
  }
}
