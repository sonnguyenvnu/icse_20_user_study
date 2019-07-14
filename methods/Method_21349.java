@OnClick(R.id.card_view) public void onClick(){
  if (this.delegate != null) {
    this.delegate.projectStateChangedPositiveClicked(this,activity());
  }
}
