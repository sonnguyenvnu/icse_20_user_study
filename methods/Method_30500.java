public ApiRequest<ItemCollection> voteItemCollection(CollectableItem.Type itemType,long itemId,long itemCollectionId){
  return mFrodoService.voteItemCollection(itemType.getApiString(),itemId,itemCollectionId);
}
