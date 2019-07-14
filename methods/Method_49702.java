@Override public void startUpdateCodeOnDrag(){
  Log.i(TAG,"Starting camera drag listener");
  mMap.setOnCameraChangeListener(new OnCameraChangeListener(){
    @Override public void onCameraChange(    CameraPosition cameraPosition){
      mListener.mapChanged(cameraPosition.target.latitude,cameraPosition.target.longitude);
    }
  }
);
  CameraPosition cameraPosition=getCameraPosition();
  mListener.mapChanged(cameraPosition.target.latitude,cameraPosition.target.longitude);
}
