protected int getDefaultReadBuffer(){
  return fboLayerEnabled ? COLOR_ATTACHMENT0 : FRONT;
}
