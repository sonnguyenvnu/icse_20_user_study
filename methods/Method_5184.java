private void addMediaSourcesInternal(int index,Collection<MediaSourceHolder> mediaSourceHolders){
  for (  MediaSourceHolder mediaSourceHolder : mediaSourceHolders) {
    addMediaSourceInternal(index++,mediaSourceHolder);
  }
}
