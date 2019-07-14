@Override public void onCheckBlocking(@NonNull String login){
  makeRestCall(RestProvider.getUserService(isEnterprise()).isUserBlocked(login),booleanResponse -> sendToView(view -> {
    isUserBlockedRequested=true;
    isUserBlocked=booleanResponse.code() == 204;
    view.onInvalidateMenu();
  }
));
}
