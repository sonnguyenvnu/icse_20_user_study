public ApiRequest<DoulistList> getItemRelatedDoulistList(CollectableItem.Type itemType,long itemId,Integer start,Integer count){
  return mFrodoService.getItemRelatedDoulistList(itemType.getApiString(),itemId,start,count);
}
