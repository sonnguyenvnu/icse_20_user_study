@Override public void onMarkAllByRepo(@NonNull Repo repo){
  getPresenter().onMarkReadByRepo(adapter.getData(),repo);
}
