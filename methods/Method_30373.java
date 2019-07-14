/** 
 * @deprecated Use {@link #onBindViewHolder(RecyclerView.ViewHolder,int,List)} instead.
 */
private void bindTrackListHolder(RecyclerView.ViewHolder holder,Music music){
  TrackListHolder trackListHolder=(TrackListHolder)holder;
  boolean playable=music.vendorCount > 0;
  ViewUtils.setVisibleOrGone(trackListHolder.playAllButton,playable);
  if (playable) {
    trackListHolder.playAllButton.setOnClickListener(view -> PlayMusicService.start(music,view.getContext()));
  }
  ViewUtils.setVisibleOrGone(trackListHolder.trackList,!music.tracks.isEmpty());
  TrackListAdapter adapter=(TrackListAdapter)trackListHolder.trackList.getAdapter();
  adapter.setMusic(music);
}
