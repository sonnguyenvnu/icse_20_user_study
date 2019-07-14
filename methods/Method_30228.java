private boolean shouldWrite(SimpleItemCollection itemCollection,Context context){
  if (itemCollection.user.isOneself()) {
    ToastUtils.show(R.string.item_collection_vote_error_cannot_vote_oneself,context);
    return false;
  }
 else   if (itemCollection.isVoted) {
    ToastUtils.show(R.string.item_collection_vote_error_cannot_vote_again,context);
    return false;
  }
 else {
    return true;
  }
}
