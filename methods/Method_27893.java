@Override public void onWorkOffline(@NonNull String login){
  User userModel=User.getUser(login);
  if (userModel == null) {
    return;
  }
  onSendUserToView(userModel);
}
