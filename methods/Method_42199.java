public void setMedia(@NonNull ArrayList<Media> mediaList){
  mediaItems=mediaList;
  selectedPositions.clear();
  buildTimelineItems();
}
