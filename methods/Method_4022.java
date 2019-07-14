/** 
 * Method to be called by subclasses when an add animation is being started.
 * @param item The item being added
 */
public final void dispatchAddStarting(RecyclerView.ViewHolder item){
  onAddStarting(item);
}
