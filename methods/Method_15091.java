@Override public void onStopLoadMore(final boolean isHaveMore){
  runUiThread(new Runnable(){
    @Override public void run(){
      lvBaseList.stopLoadMore(isHaveMore);
    }
  }
);
}
