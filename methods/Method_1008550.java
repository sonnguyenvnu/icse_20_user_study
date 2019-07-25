/** 
 * Set the destination index and type.
 */
public ReindexRequestBuilder destination(String index,String type){
  destination.setIndex(index).setType(type);
  return this;
}
