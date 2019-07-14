/** 
 * @deprecated Use {@link #onBindViewHolder(RecyclerView.ViewHolder,int,List)} instead.
 */
private void bindTrackListHolder(RecyclerView.ViewHolder holder){
  TrackListHolder trackListHolder=(TrackListHolder)holder;
  TrackListAdapter adapter=(TrackListAdapter)trackListHolder.trackList.getAdapter();
  adapter.notifyItemRangeChanged(0,adapter.getItemCount());
}
