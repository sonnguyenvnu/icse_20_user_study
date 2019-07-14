public ApiRequest<ItemCollection> collectItem(CollectableItem.Type itemType,long itemId,ItemCollectionState state,int rating,List<String> tags,String comment,List<Long> gamePlatformIds,boolean shareToBroadcast,boolean shareToWeibo,boolean shareToWeChatMoments){
  return mFrodoService.collectItem(itemType.getApiString(),itemId,state.getApiString(),rating,StringCompat.join(",",tags),comment,gamePlatformIds,shareToBroadcast ? 1 : null,shareToWeibo ? 1 : null,shareToWeChatMoments ? 1 : null);
}
