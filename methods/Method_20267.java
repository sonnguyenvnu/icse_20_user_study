/** 
 * Call this methods every time something related to ui (scroll, layout, ...) or something related to data changed.
 * @param recyclerView        the recycler view
 * @param epoxyHolder         the {@link RecyclerView}
 * @param detachEvent         true if the event originated from a view detached from therecycler view
 * @param eventOriginForDebug a debug strings used for logs
 * @return true if changed
 */
private boolean processVisibilityEvents(@NonNull RecyclerView recyclerView,@NonNull EpoxyViewHolder epoxyHolder,boolean detachEvent,String eventOriginForDebug){
  if (DEBUG_LOG) {
    Log.d(TAG,String.format("%s.processVisibilityEvents %s, %s, %s",eventOriginForDebug,System.identityHashCode(epoxyHolder),detachEvent,epoxyHolder.getAdapterPosition()));
  }
  final View itemView=epoxyHolder.itemView;
  final int id=System.identityHashCode(itemView);
  EpoxyVisibilityItem vi=visibilityIdToItemMap.get(id);
  if (vi == null) {
    vi=new EpoxyVisibilityItem(epoxyHolder.getAdapterPosition());
    visibilityIdToItemMap.put(id,vi);
    visibilityIdToItems.add(vi);
  }
 else   if (epoxyHolder.getAdapterPosition() != RecyclerView.NO_POSITION && vi.getAdapterPosition() != epoxyHolder.getAdapterPosition()) {
    vi.reset(epoxyHolder.getAdapterPosition());
  }
  boolean changed=false;
  if (vi.update(itemView,recyclerView,detachEvent)) {
    vi.handleVisible(epoxyHolder,detachEvent);
    vi.handleFocus(epoxyHolder,detachEvent);
    vi.handleFullImpressionVisible(epoxyHolder,detachEvent);
    changed=vi.handleChanged(epoxyHolder,onChangedEnabled);
  }
  return changed;
}
