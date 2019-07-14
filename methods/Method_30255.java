public BaseItemFragment<SimpleItemType,ItemType> setArguments(long itemId,SimpleItemType simpleItem,ItemType item){
  FragmentUtils.getArgumentsBuilder(this).putLong(EXTRA_ITEM_ID,itemId).putParcelable(EXTRA_SIMPLE_ITEM,simpleItem).putParcelable(EXTRA_ITEM,item);
  return this;
}
