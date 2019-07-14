/** 
 * Submits a new list to be diffed, and displayed. <p> If a list is already being displayed, a diff will be computed on a background thread, which will dispatch Adapter.notifyItem events on the main thread.
 * @param list The new list to be displayed.
 */
public void submitList(@Nullable List<T> list){
  mDiffer.submitList(list);
}
