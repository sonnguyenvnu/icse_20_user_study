@Override public void bind(@NonNull TeamsModel user){
  title.setText(!InputHelper.isEmpty(user.getName()) ? user.getName() : user.getSlug());
  if (!InputHelper.isEmpty(user.getDescription())) {
    date.setText(user.getDescription());
  }
 else {
    date.setText(InputHelper.toNA(user.getSlug()));
  }
}
