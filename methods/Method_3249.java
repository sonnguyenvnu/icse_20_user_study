/** 
 * ??? A - B + C ??????,?? ?? - ?? + ?? = ????????? ?? ?? ??
 * @param A    ??????
 * @param B    ??????
 * @param C    ??????
 * @param size topN?
 * @return ?(A - B + C)????????????????
 */
public List<Map.Entry<String,Float>> analogy(String A,String B,String C,int size){
  Vector a=storage.get(A);
  Vector b=storage.get(B);
  Vector c=storage.get(C);
  if (a == null || b == null || c == null) {
    return Collections.emptyList();
  }
  List<Map.Entry<String,Float>> resultList=nearest(a.minus(b).add(c),size + 3);
  ListIterator<Map.Entry<String,Float>> listIterator=resultList.listIterator();
  while (listIterator.hasNext()) {
    String key=listIterator.next().getKey();
    if (key.equals(A) || key.equals(B) || key.equals(C)) {
      listIterator.remove();
    }
  }
  if (resultList.size() > size) {
    resultList=resultList.subList(0,size);
  }
  return resultList;
}
