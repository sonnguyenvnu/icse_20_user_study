/** 
 * @param obj ??
 * @return ?????????
 */
public boolean match(T obj){
  if (null == obj)   return false;
  if (!isRegion()) {
    if (this.leftOpen && this.rightOpen) {
      return left.compareTo(obj) != 0;
    }
    return left.compareTo(obj) == 0;
  }
  if (null != left) {
    int c=obj.compareTo(left);
    if (c < 0 || c == 0 && leftOpen) {
      return false;
    }
  }
  if (null != right) {
    int c=obj.compareTo(right);
    if (c > 0 || c == 0 && rightOpen) {
      return false;
    }
  }
  return true;
}
