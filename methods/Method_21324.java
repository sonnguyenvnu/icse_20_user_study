@OnClick(R.id.friend_backing_card_view) public void onClick(){
  if (this.delegate != null) {
    this.delegate.friendBackingClicked(this,activity());
  }
}
