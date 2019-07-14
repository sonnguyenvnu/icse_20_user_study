@Override public void bind(@NonNull User user){
  avatar.setUrl(user.getAvatarUrl(),user.getLogin(),user.isOrganizationType(),LinkParserHelper.isEnterprise(user.getHtmlUrl()));
  title.setText(user.getLogin());
  date.setVisibility(View.GONE);
  if (onSelectAssignee != null) {
    itemView.setBackgroundColor(onSelectAssignee.isAssigneeSelected(getAdapterPosition()) ? lightGray : ViewHelper.getWindowBackground(itemView.getContext()));
  }
}
