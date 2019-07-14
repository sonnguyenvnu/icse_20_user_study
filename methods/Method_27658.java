@Override public void onStarGist(){
  isGistStarred=!isGistStarred;
  sendToView(view -> view.onGistStarred(isGistStarred));
}
