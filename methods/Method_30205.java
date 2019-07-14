protected ItemRecommendationListResource setArguments(CollectableItem.Type itemType,long itemId){
  FragmentUtils.getArgumentsBuilder(this).putSerializable(EXTRA_ITEM_TYPE,itemType).putLong(EXTRA_ITEM_ID,itemId);
  return this;
}
