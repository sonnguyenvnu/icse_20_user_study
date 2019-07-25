/** 
 * Register the specified  {@code item} with the specified {@code id}.
 * @param id the id of the item
 * @param item the item to register
 */
public void add(I id,V item){
  this.items.put(id,item);
}
