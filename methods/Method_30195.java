private static ItemCollectionListResource newInstance(CollectableItem.Type itemType,long itemId,boolean followingsFirst){
  return new ItemCollectionListResource().setArguments(itemType,itemId,followingsFirst);
}
