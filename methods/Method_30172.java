public boolean isWriting(CollectableItem.Type itemType,long itemId){
  return findWriter(itemType,itemId) != null;
}
