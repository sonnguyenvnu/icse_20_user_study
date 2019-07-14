@Override public void setOnKeyStatusChangeListener(final ExoMediaDrm.OnKeyStatusChangeListener<? super FrameworkMediaCrypto> listener){
  if (Util.SDK_INT < 23) {
    throw new UnsupportedOperationException();
  }
  mediaDrm.setOnKeyStatusChangeListener(listener == null ? null : (mediaDrm,sessionId,keyInfo,hasNewUsableKey) -> {
    List<KeyStatus> exoKeyInfo=new ArrayList<>();
    for (    MediaDrm.KeyStatus keyStatus : keyInfo) {
      exoKeyInfo.add(new KeyStatus(keyStatus.getStatusCode(),keyStatus.getKeyId()));
    }
    listener.onKeyStatusChange(FrameworkMediaDrm.this,sessionId,exoKeyInfo,hasNewUsableKey);
  }
,null);
}
