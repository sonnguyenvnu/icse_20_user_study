private MediaSource createMediaSource(){
  if (mMediaMetadatas.size() == 1) {
    return mCreateMediaSource.apply(mMediaMetadatas.get(0));
  }
  MediaSource[] mediaSources=new MediaSource[mMediaMetadatas.size()];
  for (int i=0; i < mMediaMetadatas.size(); ++i) {
    MediaMetadataCompat mediaMetadata=mMediaMetadatas.get(i);
    mediaSources[i]=mCreateMediaSource.apply(mediaMetadata);
  }
  return new ConcatenatingMediaSource(mediaSources);
}
