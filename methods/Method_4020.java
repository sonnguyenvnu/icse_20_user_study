/** 
 * Method to be called by subclasses when an add animation is done.
 * @param item The item which has been added
 */
public final void dispatchAddFinished(RecyclerView.ViewHolder item){
  onAddFinished(item);
  dispatchAnimationFinished(item);
}
