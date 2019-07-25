private void cleanup(){
  new AsyncTask<Object,Object,Object>(){
    @Override protected Long doInBackground(    Object... objects){
      Log.i(TAG,"Cleanup DNS");
      DatabaseHelper.getInstance(ActivityDns.this).cleanupDns();
      return null;
    }
    @Override protected void onPostExecute(    Object result){
      ServiceSinkhole.reload("DNS cleanup",ActivityDns.this,false);
      updateAdapter();
    }
  }
.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
}
