public void updateLiveLocations(){
  if (!currentLiveLocations.isEmpty()) {
    notifyItemRangeChanged(2,currentLiveLocations.size());
  }
}
