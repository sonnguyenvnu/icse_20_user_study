private static CelebrityListResource newInstance(CollectableItem.Type itemType,long itemId){
  return new CelebrityListResource().setArguments(itemType,itemId);
}
