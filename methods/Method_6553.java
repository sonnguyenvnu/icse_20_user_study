public void cancelLoadImage(){
  forceLoding=false;
  ImageLoader.getInstance().cancelLoadingForImageReceiver(this,true);
  canceledLoading=true;
}
