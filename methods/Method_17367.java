/** 
 * The estimate is correct within epsilon * (total item count), with probability confidence. 
 */
public long estimateCount(long item){
  long count=Long.MAX_VALUE;
  for (int i=0; i < depth; ++i) {
    count=Math.min(count,table[i][hash(item,i)]);
  }
  return count;
}
