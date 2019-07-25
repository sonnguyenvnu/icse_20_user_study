/** 
 * Remove selected items from ListView.
 * @param view
 */
protected void delete(ListView<V> view){
  MultipleSelectionModel<V> selection=view.selectionModelProperty().getValue();
  for (  int index : selection.getSelectedIndices()) {
    view.getItems().remove(index);
  }
}
