@Override public void onSuccessDownload(String name){
  progressView.setProgress(1,true);
  updateFileExistIcon();
}
