/** 
 * Returns an instance which is the concatenation of the two given instances.
 * @param list1 {@code non-null;} first instance
 * @param list2 {@code non-null;} second instance
 * @return {@code non-null;} combined instance
 */
public static LineNumberList concat(LineNumberList list1,LineNumberList list2){
  if (list1 == EMPTY) {
    return list2;
  }
  int sz1=list1.size();
  int sz2=list2.size();
  LineNumberList result=new LineNumberList(sz1 + sz2);
  for (int i=0; i < sz1; i++) {
    result.set(i,list1.get(i));
  }
  for (int i=0; i < sz2; i++) {
    result.set(sz1 + i,list2.get(i));
  }
  return result;
}
