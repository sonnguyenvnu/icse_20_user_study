public ApiRequest<ItemForumTopicList> getItemForumTopicList(CollectableItem.Type itemType,long itemId,Integer episode,Integer start,Integer count){
  return mFrodoService.getItemForumTopicList(itemType.getApiString(),itemId,episode,start,count);
}
