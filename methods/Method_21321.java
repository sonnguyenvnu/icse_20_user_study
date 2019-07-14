@Override public void bindData(final @Nullable Object data) throws Exception {
  @SuppressWarnings("unchecked") final Pair<Project,User> projectAndUser=requireNonNull((Pair<Project,User>)data);
  this.project=requireNonNull(projectAndUser.first,Project.class);
  this.user=projectAndUser.second;
}
