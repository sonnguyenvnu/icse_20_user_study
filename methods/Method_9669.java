@Override public void onProgressDownload(String fileName,float progress){
  radialProgress.setProgress(progress,progressVisible);
  if (radialProgress.getIcon() != MediaActionDrawable.ICON_EMPTY) {
    updateButtonState(radialProgress,null,this,false,true);
  }
}
