@Override public void onItemChanged(int requestCode,ItemType newItem){
  mItem=newItem;
  mSimpleItem=newItem;
  mItemId=newItem.id;
  getListener().onItemChanged(getRequestCode(),newItem);
  notifyChanged();
}
