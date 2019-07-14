/** 
 * Don't call this method directly, it is called from {@link EpoxyVisibilityTracker#processVisibilityEvents}
 * @param child               the view to process for visibility event
 * @param detachEvent         true if the child was just detached
 * @param eventOriginForDebug a debug strings used for logs
 */
private void processChild(@NonNull View child,boolean detachEvent,String eventOriginForDebug){
  final RecyclerView recyclerView=attachedRecyclerView;
  if (recyclerView != null) {
    final boolean isParentValid=child.getParent() == null || child.getParent() == recyclerView;
    final ViewHolder holder=isParentValid ? recyclerView.getChildViewHolder(child) : null;
    if (holder instanceof EpoxyViewHolder) {
      boolean changed=processVisibilityEvents(recyclerView,(EpoxyViewHolder)holder,detachEvent,eventOriginForDebug);
      if (changed) {
        if (child instanceof RecyclerView) {
          EpoxyVisibilityTracker tracker=nestedTrackers.get(child);
          if (tracker != null) {
            tracker.processChangeEvent("parent");
          }
        }
      }
    }
  }
}
