/** 
 * Sets base and all cells to the given value.
 */
final void internalReset(long initialValue){
  Cell[] as=cells;
  base=initialValue;
  if (as != null) {
    int n=as.length;
    for (int i=0; i < n; ++i) {
      Cell a=as[i];
      if (a != null)       a.value=initialValue;
    }
  }
}
