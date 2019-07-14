private static RatingResource newInstance(CollectableItem.Type itemType,long itemId){
  return new RatingResource().setArguments(itemType,itemId);
}
