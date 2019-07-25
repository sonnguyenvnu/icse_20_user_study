@Override public void reset(){
  CommonLogger.e("reset");
  if (disposable != null && !disposable.isDisposed()) {
    disposable.dispose();
  }
  errorContainer.setVisibility(GONE);
  loadingContainer.setVisibility(GONE);
  finishContainer.setVisibility(GONE);
  bottomContainer.setVisibility(GONE);
  positionContainer.setVisibility(GONE);
  volumeContainer.setVisibility(GONE);
  brightContainer.setVisibility(GONE);
  bg.setVisibility(VISIBLE);
  middlePlay.setVisibility(VISIBLE);
  cancelTimer();
}
