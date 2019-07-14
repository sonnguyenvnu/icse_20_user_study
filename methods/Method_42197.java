private void displayMedia(TimelineItem timelineItem){
  for (int pos=0; pos < mediaItems.size(); pos++) {
    Media mediaItem=mediaItems.get(pos);
    if (mediaItem.equals(timelineItem)) {
      actionsListener.onItemSelected(pos);
      return;
    }
  }
}
