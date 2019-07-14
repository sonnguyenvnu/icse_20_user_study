@Override public void onTokenResponse(@Nullable AccessTokenModel modelResponse){
  if (modelResponse != null) {
    String token=modelResponse.getToken() != null ? modelResponse.getToken() : modelResponse.getAccessToken();
    if (!InputHelper.isEmpty(token)) {
      PrefGetter.setToken(token);
      makeRestCall(RestProvider.getUserService(false).getUser(),this::onUserResponse);
      return;
    }
  }
  sendToView(view -> view.showMessage(R.string.error,R.string.failed_login));
}
