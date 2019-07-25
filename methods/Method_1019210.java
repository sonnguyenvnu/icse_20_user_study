/** 
 * Release the actual payload, to ease memory pressure. To be called after the record has been written to storage. Once deflated, cannot be inflated.
 */
public void deflate(){
  this.data=null;
}
