private void clearTarget(int requestId){
  ImageDownloadTarget target=mRequestTargetMap.remove(requestId);
  if (target != null) {
    mRequestManager.clear(target);
  }
}
