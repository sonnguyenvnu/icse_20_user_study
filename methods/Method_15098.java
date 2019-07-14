/** 
 * ????
 * @param page
 * @param newList ?????
 * @param isCache
 * @return
 * @return
 */
public synchronized void handleList(int page,List<T> newList,boolean isCache){
  if (newList == null) {
    newList=new ArrayList<T>();
  }
  isSucceed=!newList.isEmpty();
  Log.i(TAG,"\n\n<<<<<<<<<<<<<<<<<\n handleList  newList.size = " + newList.size() + "; isCache = " + isCache + "; page = " + page + "; isSucceed = " + isSucceed);
  if (page <= HttpManager.PAGE_NUM_0) {
    Log.i(TAG,"handleList  page <= HttpManager.PAGE_NUM_0 >>>>  ");
    saveCacheStart=0;
    list=new ArrayList<T>(newList);
    if (isCache == false && list.isEmpty() == false) {
      Log.i(TAG,"handleList  isCache == false && list.isEmpty() == false >>  isToLoadCache = false;");
      isToLoadCache=false;
    }
  }
 else {
    Log.i(TAG,"handleList  page > HttpManager.PAGE_NUM_0 >>>>  ");
    if (list == null) {
      list=new ArrayList<T>();
    }
    saveCacheStart=list.size();
    isHaveMore=!newList.isEmpty();
    if (isHaveMore) {
      list.addAll(newList);
    }
  }
  Log.i(TAG,"handleList  list.size = " + list.size() + "; isHaveMore = " + isHaveMore + "; isToLoadCache = " + isToLoadCache + "; saveCacheStart = " + saveCacheStart + "\n>>>>>>>>>>>>>>>>>>\n\n");
}
