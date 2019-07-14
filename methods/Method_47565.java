public void postSetSelection(final int position){
  clearFocus();
  post(new Runnable(){
    @Override public void run(){
      setSelection(position);
    }
  }
);
  onScrollStateChanged(this,OnScrollListener.SCROLL_STATE_IDLE);
}
