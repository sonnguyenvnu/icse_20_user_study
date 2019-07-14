protected ItemForumTopicListResource setArguments(CollectableItem.Type itemType,long itemId,Integer episode){
  FragmentUtils.getArgumentsBuilder(this).putSerializable(EXTRA_ITEM_TYPE,itemType).putLong(EXTRA_ITEM_ID,itemId).putSerializable(EXTRA_EPISODE,episode);
  return this;
}
