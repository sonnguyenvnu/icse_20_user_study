@OnClick(R.id.card_view) public void stateChangeCardClick(){
  if (this.delegate != null) {
    this.delegate.projectStateChangedClicked(this,activity());
  }
}
