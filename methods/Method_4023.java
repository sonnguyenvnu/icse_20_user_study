/** 
 * Method to be called by subclasses when a change animation is being started.
 * @param item    The item which has been changed (this method must be called foreach non-null ViewHolder passed into {@link #animateChange(RecyclerView.ViewHolder,RecyclerView.ViewHolder,int,int,int,int)}).
 * @param oldItem true if this is the old item that was changed, false ifit is the new item that replaced the old item.
 */
public final void dispatchChangeStarting(RecyclerView.ViewHolder item,boolean oldItem){
  onChangeStarting(item,oldItem);
}
