@Override public void checkStarring(@NonNull String gistId){
  makeRestCall(RestProvider.getGistService(isEnterprise()).checkGistStar(gistId),booleanResponse -> {
    isGistStarred=booleanResponse.code() == 204;
    sendToView(view -> view.onGistStarred(isGistStarred));
  }
);
}
