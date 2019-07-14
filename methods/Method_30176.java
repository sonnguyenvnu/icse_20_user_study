public List<String> getTags(CollectableItem.Type itemType,long itemId){
  return findWriter(itemType,itemId).getTags();
}
