private void setHandlePosition1(float relativePos){
  handle.setY(Utils.clamp(0,getHeightMinusPadding() - handle.getHeight(),relativePos * (getHeightMinusPadding() - handle.getHeight())));
}
