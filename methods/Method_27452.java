private void setup(User user,String description,ReactionsModel reactionsModel){
  avatar.setUrl(user.getAvatarUrl(),user.getLogin(),user.isOrganizationType(),LinkParserHelper.isEnterprise(user.getHtmlUrl()));
  name.setText(user.getLogin());
  boolean isOwner=TextUtils.equals(repoOwner,user.getLogin());
  if (isOwner) {
    owner.setVisibility(View.VISIBLE);
    owner.setText(R.string.owner);
  }
 else {
    owner.setText("");
    owner.setVisibility(View.GONE);
  }
  if (reactionsModel != null) {
    appendEmojies(reactionsModel);
  }
  if (!InputHelper.isEmpty(description)) {
    HtmlHelper.htmlIntoTextView(comment,description,viewGroup.getWidth() - ViewHelper.dpToPx(itemView.getContext(),24));
  }
 else {
    comment.setText(R.string.no_description_provided);
  }
}
