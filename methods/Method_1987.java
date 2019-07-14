/** 
 * Stops the current gesture and calls the listener right after stopping it.
 */
private void stopGesture(){
  if (mGestureInProgress) {
    mGestureInProgress=false;
    if (mListener != null) {
      mListener.onGestureEnd(this);
    }
  }
}
