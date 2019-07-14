protected int getDefaultDrawBuffer(){
  return fboLayerEnabled ? COLOR_ATTACHMENT0 : BACK;
}
