/** 
 * Sets the documents from which the terms should not be selected from.
 */
public MoreLikeThisQueryBuilder unlike(Item[] unlikeItems){
  this.unlikeItems=Optional.ofNullable(unlikeItems).orElse(new Item[0]);
  return this;
}
