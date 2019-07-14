@Override public void stopUpdateCodeOnDrag(){
  Log.i(TAG,"Stopping camera drag listener");
  mMap.setOnCameraChangeListener(null);
}
