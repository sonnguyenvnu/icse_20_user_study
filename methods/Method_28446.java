@Override public void onBlockUser(@NonNull String login){
  if (isUserBlocked()) {
    onUnblockUser(login);
  }
 else {
    makeRestCall(RestProvider.getUserService(isEnterprise()).blockUser(login),booleanResponse -> sendToView(view -> {
      isUserBlocked=true;
      view.onUserBlocked();
    }
));
  }
}
