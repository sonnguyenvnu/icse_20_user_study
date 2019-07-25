public void reset(TimePeriod time){
  this.time=time;
  new LoadData(true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
}
