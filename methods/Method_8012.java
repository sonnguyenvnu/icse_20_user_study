@Override public void onSuccessDownload(String fileName){
  radialProgress.setProgress(1,true);
  updateButtonState(false,true);
}
