private void onSendUserToView(User userModel){
  sendToView(view -> view.onInitViews(userModel));
}
