@Override public void onProgressDownload(String fileName,float progress){
  if (!progressVisible) {
    checkFileExist();
  }
  setProgress(progress,true);
}
