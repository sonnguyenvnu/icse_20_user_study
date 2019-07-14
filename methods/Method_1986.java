/** 
 * Starts a new gesture and calls the listener just before starting it.
 */
private void startGesture(){
  if (!mGestureInProgress) {
    if (mListener != null) {
      mListener.onGestureBegin(this);
    }
    mGestureInProgress=true;
  }
}
