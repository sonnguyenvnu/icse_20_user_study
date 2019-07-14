@Override public void onHandleComment(@NonNull String text,@Nullable Bundle bundle){
  getPresenter().onHandleComment(text,bundle,gistId);
}
