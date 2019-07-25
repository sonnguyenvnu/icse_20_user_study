/** 
 * Sets the  {@link SortOrder} to use to sort values produced this source
 */
@SuppressWarnings("unchecked") public AB order(String order){
  if (order == null) {
    throw new IllegalArgumentException("[order] must not be null");
  }
  this.order=SortOrder.fromString(order);
  return (AB)this;
}
