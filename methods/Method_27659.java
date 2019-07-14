@Override public void onForkGist(){
  isGistForked=!isGistForked;
  sendToView(view -> view.onGistForked(isGistForked));
}
