private void clear(){
  new AsyncTask<Object,Object,Object>(){
    @Override protected Long doInBackground(    Object... objects){
      Log.i(TAG,"Clear DNS");
      DatabaseHelper.getInstance(ActivityDns.this).clearDns();
      return null;
    }
    @Override protected void onPostExecute(    Object result){
      ServiceSinkhole.reload("DNS clear",ActivityDns.this,false);
      updateAdapter();
    }
  }
.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
}
