@Override public void showErrorMessage(@NonNull String message){
  onHideProgress();
  super.showErrorMessage(message);
}
