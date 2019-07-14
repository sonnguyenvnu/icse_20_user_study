@Override public void onResponse(ItemCollection response){
  Context context=getContext();
  ToastUtils.show(context.getString(R.string.item_uncollect_successful_format,mItemType.getName(context)),context);
  EventBusUtils.postAsync(new ItemUncollectedEvent(mItemType,mItemId,this));
  stopSelf();
}
