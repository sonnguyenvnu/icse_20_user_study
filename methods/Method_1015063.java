@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  payload.localMediaPath=this.localPath;
  payload.remoteMediaUrl=this.remoteUrl;
  payload.mediaType=mediaType;
  return payload;
}
