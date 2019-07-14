/** 
 * @param requestCode  = -page {@link #getListAsync(int)}
 * @param resultJson  
 * @param e  
 */
@Override public void onHttpResponse(final int requestCode,final String resultJson,final Exception e){
  runThread(TAG + "onHttpResponse",new Runnable(){
    @Override public void run(){
      int page=0;
      if (requestCode > 0) {
        Log.w(TAG,"requestCode > 0, ???BaseListFragment#getListAsync(int page)??page?????requestCode!");
      }
 else {
        page=-requestCode;
      }
      List<T> array=parseArray(resultJson);
      if ((array == null || array.isEmpty()) && e != null) {
        onLoadFailed(page,e);
      }
 else {
        onLoadSucceed(page,array);
      }
    }
  }
);
}
