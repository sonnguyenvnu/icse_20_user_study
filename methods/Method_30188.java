protected GameGuideListResource setArguments(long itemId){
  FragmentUtils.getArgumentsBuilder(this).putLong(EXTRA_ITEM_ID,itemId);
  return this;
}
