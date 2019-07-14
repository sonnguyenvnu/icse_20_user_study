private TrackListHolder createTrackListHolder(ViewGroup parent){
  TrackListHolder holder=new TrackListHolder(ViewUtils.inflate(R.layout.item_fragment_track_list,parent));
  holder.trackList.setAdapter(new TrackListAdapter(getListener()));
  return holder;
}
