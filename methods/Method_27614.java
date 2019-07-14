@Override public void onRemove(@NonNull Comment comment){
  hideProgress();
  adapter.removeItem(comment);
}
