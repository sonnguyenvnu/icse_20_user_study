@Override public DrmSession<T> acquireSession(Looper playbackLooper,DrmInitData drmInitData){
  Assertions.checkState(this.playbackLooper == null || this.playbackLooper == playbackLooper);
  if (sessions.isEmpty()) {
    this.playbackLooper=playbackLooper;
    if (mediaDrmHandler == null) {
      mediaDrmHandler=new MediaDrmHandler(playbackLooper);
    }
  }
  List<SchemeData> schemeDatas=null;
  if (offlineLicenseKeySetId == null) {
    schemeDatas=getSchemeDatas(drmInitData,uuid,false);
    if (schemeDatas.isEmpty()) {
      final MissingSchemeDataException error=new MissingSchemeDataException(uuid);
      eventDispatcher.dispatch(listener -> listener.onDrmSessionManagerError(error));
      return new ErrorStateDrmSession<>(new DrmSessionException(error));
    }
  }
  DefaultDrmSession<T> session;
  if (!multiSession) {
    session=sessions.isEmpty() ? null : sessions.get(0);
  }
 else {
    session=null;
    for (    DefaultDrmSession<T> existingSession : sessions) {
      if (Util.areEqual(existingSession.schemeDatas,schemeDatas)) {
        session=existingSession;
        break;
      }
    }
  }
  if (session == null) {
    session=new DefaultDrmSession<>(uuid,mediaDrm,this,schemeDatas,mode,offlineLicenseKeySetId,optionalKeyRequestParameters,callback,playbackLooper,eventDispatcher,initialDrmRequestRetryCount);
    sessions.add(session);
  }
  session.acquire();
  return session;
}
