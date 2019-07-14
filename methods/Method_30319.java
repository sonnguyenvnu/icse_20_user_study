@Subscribe(threadMode=ThreadMode.POSTING) public void onItemUncollected(ItemUncollectedEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (event.itemType == mItem.getType() && event.itemId == mItem.id) {
    finish();
  }
}
