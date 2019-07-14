private void buildTimelineItems(){
  clearAll();
  timelineItems=getTimelineItems(mediaItems);
  notifyDataSetChanged();
}
