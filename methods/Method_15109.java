/** 
 * ???? isCache = false;
 * @param page
 * @param newList
 */
public synchronized void onLoadSucceed(final int page,final List<T> newList){
  onLoadSucceed(page,newList,false);
}
