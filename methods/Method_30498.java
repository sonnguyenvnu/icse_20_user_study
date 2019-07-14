public ApiRequest<ItemCollection> uncollectItem(CollectableItem.Type itemType,long itemId){
  return mFrodoService.uncollectItem(itemType.getApiString(),itemId);
}
