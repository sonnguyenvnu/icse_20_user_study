/** 
 * Sets all items as not selected.
 */
@Override public void clearSelection(){
  selected.clear();
  notifyDataSetChanged();
  observable.notifyListeners();
}
