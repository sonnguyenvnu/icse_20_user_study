@Override public void onSelectedAssignees(@NonNull ArrayList<User> users,boolean isAssignees){
  this.users.clear();
  this.users.addAll(users);
  SpannableBuilder builder=SpannableBuilder.builder();
  for (int i=0; i < users.size(); i++) {
    User user=users.get(i);
    builder.append(user.getLogin());
    if (i != users.size() - 1) {
      builder.append(", ");
    }
  }
  assignee.setText(builder);
}
