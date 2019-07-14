/** 
 * Finds index of given element in inclusive index range. Returns negative value if element is not found.
 */
public int find(int low,int high){
  while (low <= high) {
    int mid=(low + high) >>> 1;
    int delta=compare(mid);
    if (delta < 0) {
      low=mid + 1;
    }
 else     if (delta > 0) {
      high=mid - 1;
    }
 else {
      return mid;
    }
  }
  return -(low + 1);
}
