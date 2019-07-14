@CallSuper protected void onDestroy(){
  Timber.d("onDestroy %s",this.toString());
  dropView();
}
