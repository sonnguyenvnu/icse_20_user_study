/** 
 * Set the target index statuses.   {@link #call()} will repeatedlypoll the graph passed into this instance during construction to see whether the index (also passed in during construction) has one of the the supplied statuses.
 * @param statuses
 * @return
 */
public S status(SchemaStatus... statuses){
  this.statuses.clear();
  this.statuses.addAll(Arrays.asList(statuses));
  return self();
}
