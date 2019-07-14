public void setTransformHintToHeader(boolean value){
  if (transformHintToHeader == value) {
    return;
  }
  transformHintToHeader=value;
  if (headerTransformAnimation != null) {
    headerTransformAnimation.cancel();
    headerTransformAnimation=null;
  }
}
