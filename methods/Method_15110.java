/** 
 * ????
 * @param page
 * @param newList
 * @param isCache newList?????
 */
private synchronized void onLoadSucceed(final int page,final List<T> newList,final boolean isCache){
  runThread(TAG + "onLoadSucceed",new Runnable(){
    @Override public void run(){
      Log.i(TAG,"onLoadSucceed  page = " + page + "; isCache = " + isCache + " >> handleList...");
      handleList(page,newList,isCache);
      runUiThread(new Runnable(){
        @Override public void run(){
          stopLoadData(page,isCache);
          setList(list);
        }
      }
);
      if (isToSaveCache && isCache == false) {
        saveCache(newList);
      }
    }
  }
);
}
