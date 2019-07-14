@Override public void bind(@NonNull GroupedNotificationModel model){
  Repo repo=model.getRepo();
  if (repo != null && headerTitle != null) {
    headerTitle.setText(repo.getFullName());
  }
}
