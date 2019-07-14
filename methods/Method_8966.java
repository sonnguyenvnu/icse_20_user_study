@Override public void onProgressDownload(String fileName,float progress){
  progressView.setProgress(progress);
  if (buttonState != 3) {
    updateButtonState();
  }
  invalidate();
}
