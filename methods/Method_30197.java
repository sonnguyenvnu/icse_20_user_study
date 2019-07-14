protected ItemCollectionListResource setArguments(CollectableItem.Type itemType,long itemId,boolean followingsFirst){
  FragmentUtils.getArgumentsBuilder(this).putSerializable(EXTRA_ITEM_TYPE,itemType).putLong(EXTRA_ITEM_ID,itemId).putBoolean(EXTRA_FOLLOWINGS_FIRST,followingsFirst);
  return this;
}
