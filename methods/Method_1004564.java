protected void dispatch(final MapEvent event){
  if (callback != null) {
    handler.removeCallbacks(callback);
  }
  callback=new CallbackTask(event);
  handler.postDelayed(callback,delay);
}
