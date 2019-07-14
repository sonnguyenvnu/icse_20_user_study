@GuardedBy("this") private void addPublicMediaSources(int index,Collection<MediaSource> mediaSources,@Nullable Handler handler,@Nullable Runnable actionOnCompletion){
  Assertions.checkArgument((handler == null) == (actionOnCompletion == null));
  for (  MediaSource mediaSource : mediaSources) {
    Assertions.checkNotNull(mediaSource);
  }
  List<MediaSourceHolder> mediaSourceHolders=new ArrayList<>(mediaSources.size());
  for (  MediaSource mediaSource : mediaSources) {
    mediaSourceHolders.add(new MediaSourceHolder(mediaSource));
  }
  mediaSourcesPublic.addAll(index,mediaSourceHolders);
  if (playbackThreadHandler != null && !mediaSources.isEmpty()) {
    playbackThreadHandler.obtainMessage(MSG_ADD,new MessageData<>(index,mediaSourceHolders,handler,actionOnCompletion)).sendToTarget();
  }
 else   if (actionOnCompletion != null && handler != null) {
    handler.post(actionOnCompletion);
  }
}
