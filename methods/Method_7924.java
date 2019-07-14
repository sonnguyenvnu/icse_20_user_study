@Override public void onFailedDownload(String fileName,boolean canceled){
  updateButtonState(true,canceled);
}
