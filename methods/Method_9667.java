@Override public void onFailedDownload(String fileName,boolean canceled){
  updateButtonState(radialProgress,null,this,true,canceled);
}
