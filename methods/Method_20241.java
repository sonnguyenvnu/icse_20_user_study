private boolean recyclerViewHasSelection(RecyclerView recyclerView){
  return recyclerView.getTag(R.id.epoxy_touch_helper_selection_status) != null;
}
