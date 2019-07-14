@Override public void onProgressDownload(String fileName,float progress){
  if (progressView.getVisibility() != VISIBLE) {
    updateFileExistIcon();
  }
  progressView.setProgress(progress,true);
}
