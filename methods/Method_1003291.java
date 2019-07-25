/** 
 * Register the table filter and lookup batch.
 * @param filter table filter
 * @param lookupBatch lookup batch
 */
public void register(TableFilter filter,IndexLookupBatch lookupBatch){
  assert filter != null;
  top=new JoinFilter(lookupBatch,filter,top);
  filters[top.id]=top;
}
