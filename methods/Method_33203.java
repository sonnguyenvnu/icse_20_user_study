/** 
 * {@inheritDoc}
 */
@Override public void sort(){
  getSelectionModel().clearSelection();
  super.sort();
  if (itemWasSelected) {
    getSelectionModel().select(0);
  }
}
