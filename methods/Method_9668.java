@Override public void onSuccessDownload(String fileName){
  radialProgress.setProgress(1,progressVisible);
  updateButtonState(radialProgress,null,this,false,true);
}
