@Override public void recycle(){
  DownloadController.getInstance(parentMessageObject.currentAccount).removeLoadingFileObserver(this);
  parentView=null;
  parentMessageObject=null;
}
