@Override public void onSubscribed(boolean cancelable){
  sendToView(view -> view.showProgress(0));
}
