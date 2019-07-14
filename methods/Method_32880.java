private void setDecsetinternalBit(int internalBit,boolean set){
  if (set) {
    if (internalBit == DECSET_BIT_MOUSE_TRACKING_PRESS_RELEASE) {
      setDecsetinternalBit(DECSET_BIT_MOUSE_TRACKING_BUTTON_EVENT,false);
    }
 else     if (internalBit == DECSET_BIT_MOUSE_TRACKING_BUTTON_EVENT) {
      setDecsetinternalBit(DECSET_BIT_MOUSE_TRACKING_PRESS_RELEASE,false);
    }
  }
  if (set) {
    mCurrentDecSetFlags|=internalBit;
  }
 else {
    mCurrentDecSetFlags&=~internalBit;
  }
}
