@Override protected boolean onStateChange(int[] state){
  boolean stateChanged=false;
  for (int i=0; i < mLayers.length; i++) {
    Drawable drawable=mLayers[i];
    if (drawable != null && drawable.setState(state)) {
      stateChanged=true;
    }
  }
  return stateChanged;
}
