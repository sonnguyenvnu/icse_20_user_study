private void ensureArguments(){
  if (mItemId != ITEM_ID_INVALID) {
    return;
  }
  Bundle arguments=getArguments();
  mItem=arguments.getParcelable(EXTRA_ITEM);
  if (mItem != null) {
    mSimpleItem=mItem;
    mItemId=mItem.id;
  }
 else {
    mSimpleItem=arguments.getParcelable(EXTRA_SIMPLE_ITEM);
    if (mSimpleItem != null) {
      mItemId=mSimpleItem.id;
    }
 else {
      mItemId=arguments.getLong(EXTRA_ITEM_ID);
    }
  }
}
