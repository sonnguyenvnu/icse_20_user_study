private void detachController(){
  if (!mIsControllerAttached) {
    return;
  }
  mEventTracker.recordEvent(Event.ON_DETACH_CONTROLLER);
  mIsControllerAttached=false;
  if (isControllerValid()) {
    mController.onDetach();
  }
}
