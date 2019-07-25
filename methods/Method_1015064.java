@Override public void decode(MessagePayload payload){
  this.localPath=payload.localMediaPath;
  this.remoteUrl=payload.remoteMediaUrl;
  this.mediaType=payload.mediaType;
}
