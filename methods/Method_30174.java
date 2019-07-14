public ItemCollectionState getState(CollectableItem.Type itemType,long itemId){
  return findWriter(itemType,itemId).getState();
}
