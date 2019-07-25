@ReactMethod public void add(ReadableArray tracks,final String insertBeforeId,final Promise callback){
  final ArrayList bundleList=Arguments.toList(tracks);
  waitForConnection(() -> {
    List<Track> trackList;
    try {
      trackList=Track.createTracks(getReactApplicationContext(),bundleList,binder.getRatingType());
    }
 catch (    Exception ex) {
      callback.reject("invalid_track_object",ex);
      return;
    }
    List<Track> queue=binder.getPlayback().getQueue();
    int index=-1;
    if (insertBeforeId != null) {
      for (int i=0; i < queue.size(); i++) {
        if (queue.get(i).id.equals(insertBeforeId)) {
          index=i;
          break;
        }
      }
    }
 else {
      index=queue.size();
    }
    if (index == -1) {
      callback.reject("track_not_in_queue","Given track ID was not found in queue");
    }
 else     if (trackList == null || trackList.isEmpty()) {
      callback.reject("invalid_track_object","Track is missing a required key");
    }
 else     if (trackList.size() == 1) {
      binder.getPlayback().add(trackList.get(0),index,callback);
    }
 else {
      binder.getPlayback().add(trackList,index,callback);
    }
  }
);
}
