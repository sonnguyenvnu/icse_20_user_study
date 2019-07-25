public void skip(String id,Promise promise){
  if (id == null || id.isEmpty()) {
    promise.reject("invalid_id","The ID can't be null or empty");
    return;
  }
  for (int i=0; i < queue.size(); i++) {
    if (id.equals(queue.get(i).id)) {
      lastKnownWindow=player.getCurrentWindowIndex();
      lastKnownPosition=player.getCurrentPosition();
      player.seekToDefaultPosition(i);
      promise.resolve(null);
      return;
    }
  }
  promise.reject("track_not_in_queue","Given track ID was not found in queue");
}
