/** 
 * ROOT ????
 * @param start < 0 ? all : [start, end] 
 * @param end
 * @return
 */
public List<T> getValueList(int start,int end){
  List<T> list=getAllValueList();
  return start < 0 || start > end || list == null || end >= list.size() ? list : list.subList(start,end);
}
