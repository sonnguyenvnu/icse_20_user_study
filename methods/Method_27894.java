@Override public void onSendUserToView(@Nullable User userModel){
  sendToView(view -> view.onInitViews(userModel));
}
