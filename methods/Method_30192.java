private static ItemAwardListResource newInstance(CollectableItem.Type itemType,long itemId){
  return new ItemAwardListResource().setArguments(itemType,itemId);
}
