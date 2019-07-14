@Override public void bindData(final @Nullable Object data) throws Exception {
  @SuppressWarnings("unchecked") final Pair<Project,Comment> projectAndComment=requireNonNull((Pair<Project,Comment>)data);
  this.project=requireNonNull(projectAndComment.first,Project.class);
  this.comment=requireNonNull(projectAndComment.second,Comment.class);
}
