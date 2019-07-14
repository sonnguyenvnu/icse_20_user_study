@ThreadConfined(ThreadConfined.UI) private void dispatchNewLayoutStateReady(){
  final NewLayoutStateReadyListener listener=mNewLayoutStateReadyListener;
  if (listener != null) {
    listener.onNewLayoutStateReady(this);
  }
}
