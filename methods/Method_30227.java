/** 
 * @deprecated Use {@link #write(CollectableItem.Type,long,SimpleItemCollection,Context)}instead.
 */
public void write(CollectableItem.Type itemType,long itemId,long itemCollectionId,Context context){
  add(new VoteItemCollectionWriter(itemType,itemId,itemCollectionId,this),context);
}
