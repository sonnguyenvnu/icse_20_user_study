public void execute(MapboxMap map,final OnCameraCompleteListener listener){
  final MapboxMap.CancelableCallback callback=new MapboxMap.CancelableCallback(){
    @Override public void onCancel(){
      handleCallbackResponse(listener,true);
    }
    @Override public void onFinish(){
      handleCallbackResponse(listener,false);
    }
  }
;
  if (mCameraMode == CameraMode.FLIGHT) {
    map.animateCamera(mCameraUpdate,mDuration,callback);
  }
 else   if (mCameraMode == CameraMode.EASE) {
    map.easeCamera(mCameraUpdate,mDuration,callback);
  }
 else {
    map.moveCamera(mCameraUpdate,callback);
  }
}
