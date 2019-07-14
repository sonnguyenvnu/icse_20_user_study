/** 
 * Method to be called by subclasses when a remove animation is being started.
 * @param item The item being removed
 */
public final void dispatchRemoveStarting(RecyclerView.ViewHolder item){
  onRemoveStarting(item);
}
