/** 
 * Finds very last index of given element in inclusive index range. Returns negative value if element is not found.
 */
public int findLast(int low,int high){
  int ndx=-1;
  while (low <= high) {
    int mid=(low + high) >>> 1;
    int delta=compare(mid);
    if (delta > 0) {
      high=mid - 1;
    }
 else {
      if (delta == 0) {
        ndx=mid;
      }
      low=mid + 1;
    }
  }
  if (ndx == -1) {
    return -(low + 1);
  }
  return ndx;
}
