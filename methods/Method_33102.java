private void updateOverAllSelectedIndex(){
  if (this.getSelectionModel().getSelectedIndex() != -1) {
    int selectedIndex=this.getSelectionModel().getSelectedIndex();
    Iterator<Map.Entry<Integer,JFXListView<?>>> itr=sublistsIndices.entrySet().iterator();
    int preItemsSize=0;
    while (itr.hasNext()) {
      Map.Entry<Integer,JFXListView<?>> entry=itr.next();
      if (entry.getKey() < selectedIndex) {
        preItemsSize+=entry.getValue().getItems().size() - 1;
      }
    }
    overAllIndexProperty.set(selectedIndex + preItemsSize);
  }
 else {
    Iterator<Map.Entry<Integer,JFXListView<?>>> itr=sublistsIndices.entrySet().iterator();
    ArrayList<Object> selectedList=new ArrayList<>();
    while (itr.hasNext()) {
      Map.Entry<Integer,JFXListView<?>> entry=itr.next();
      if (entry.getValue().getSelectionModel().getSelectedIndex() != -1) {
        selectedList.add(entry.getKey());
      }
    }
    if (selectedList.size() > 0) {
      itr=sublistsIndices.entrySet().iterator();
      int preItemsSize=0;
      while (itr.hasNext()) {
        Map.Entry<Integer,JFXListView<?>> entry=itr.next();
        if (entry.getKey() < ((Integer)selectedList.get(0))) {
          preItemsSize+=entry.getValue().getItems().size() - 1;
        }
      }
      overAllIndexProperty.set(preItemsSize + (Integer)selectedList.get(0) + sublistsIndices.get(selectedList.get(0)).getSelectionModel().getSelectedIndex());
    }
 else {
      overAllIndexProperty.set(-1);
    }
  }
}
