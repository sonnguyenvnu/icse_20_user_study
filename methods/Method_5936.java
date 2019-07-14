/** 
 * Sets the limit.
 * @param limit The limit to set.
 */
public void setLimit(int limit){
  Assertions.checkArgument(limit >= 0 && limit <= data.length);
  this.limit=limit;
}
