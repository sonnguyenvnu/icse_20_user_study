public void add(){
  if (!hasInitialized()) {
    return;
  }
  String userBackgroundID=UserLocationLayerConstants.BACKGROUND_LAYER_ID;
  Layer userLocationBackgroundLayer=mMap.getLayer(userBackgroundID);
  if (userLocationBackgroundLayer != null) {
    mMap.addLayerBelow(mLayer,userBackgroundID);
    return;
  }
  mMap.addLayer(mLayer);
}
