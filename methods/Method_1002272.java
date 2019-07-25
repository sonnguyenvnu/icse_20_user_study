public void execute(final MapboxMap map){
  if (mQueue.isEmpty()) {
    if (mCompleteListener != null) {
      mCompleteListener.onCompleteAll();
    }
    return;
  }
  final CameraStop stop=mQueue.poll();
  if (stop == null) {
    return;
  }
  final CameraUpdateItem item=stop.toCameraUpdate();
  item.execute(map,new CameraUpdateItem.OnCameraCompleteListener(){
    @Override public void onComplete(){
      execute(map);
    }
  }
);
}
