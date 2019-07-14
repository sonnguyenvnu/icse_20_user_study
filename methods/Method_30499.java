public ApiRequest<ItemCollectionList> getItemCollectionList(CollectableItem.Type itemType,long itemId,boolean followingsFirst,Integer start,Integer count){
  return mFrodoService.getItemCollectionList(itemType.getApiString(),itemId,followingsFirst ? 1 : null,start,count);
}
