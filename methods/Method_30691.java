private static ItemReviewListResource newInstance(CollectableItem.Type itemType,long itemId){
  return new ItemReviewListResource().setArguments(itemType,itemId);
}
