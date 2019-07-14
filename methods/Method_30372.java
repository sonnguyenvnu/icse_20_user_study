private void bindTrackListHolder(RecyclerView.ViewHolder holder,Music music,@NonNull List<Object> payloads){
  if (payloads.isEmpty()) {
    bindTrackListHolder(holder,music);
  }
 else {
    bindTrackListHolder(holder);
  }
}
