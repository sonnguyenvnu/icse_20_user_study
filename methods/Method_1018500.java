/** 
 * Merge the two sorted subarrays (srcLow,srcMid] and (srcMid,srcHigh] into dest.
 * @param src source array for merge
 * @param srcLow lower bound of bottom sorted half
 * @param srcMid upper bound of bottom sorted half & lower bound of top sortedhalf
 * @param srcHigh upper bound of top sorted half
 * @param dest destination array for merge
 * @param destLow lower bound of destination
 * @param destHigh upper bound of destination
 * @param comp comparator to use
 */
private static void merge(Object[] src,int srcLow,int srcMid,int srcHigh,Object[] dest,int destLow,int destHigh,Comparator<Object> comp){
  int topIdx=srcMid;
  while (destLow < destHigh) {
    if (topIdx >= srcHigh || (srcLow < srcMid && comp.compare(src[srcLow],src[topIdx]) <= 0)) {
      dest[destLow++]=src[srcLow++];
    }
 else {
      dest[destLow++]=src[topIdx++];
    }
  }
}
