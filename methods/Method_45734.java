/** 
 * ??list???????????
 * @param left  ??List
 * @param right ??List
 * @param < T >   ????
 * @return ????
 */
public static <T>boolean listEquals(List<T> left,List<T> right){
  if (left == null) {
    return right == null;
  }
 else {
    if (right == null) {
      return false;
    }
    if (left.size() != right.size()) {
      return false;
    }
    List<T> ltmp=new ArrayList<T>(left);
    List<T> rtmp=new ArrayList<T>(right);
    for (    T t : ltmp) {
      rtmp.remove(t);
    }
    return rtmp.isEmpty();
  }
}
