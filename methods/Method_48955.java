/** 
 * Removes any query partition restriction for this query
 * @return
 */
public Q noPartitionRestriction(){
  this.restrict2Partitions=false;
  return getThis();
}
