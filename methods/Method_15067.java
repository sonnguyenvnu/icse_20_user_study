/** 
 * ????
 */
public synchronized void refresh(List<T> list){
  this.list=list == null ? null : new ArrayList<T>(list);
  notifyDataSetChanged();
}
