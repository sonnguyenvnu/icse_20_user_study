@Override public void bind(@NonNull ReviewCommentModel commentModel){
  if (commentModel.getUser() != null) {
    avatarView.setUrl(commentModel.getUser().getAvatarUrl(),commentModel.getUser().getLogin(),commentModel.getUser().isOrganizationType(),LinkParserHelper.isEnterprise(commentModel.getHtmlUrl()));
    name.setText(commentModel.getUser().getLogin());
    if (commentModel.getAuthorAssociation() != null && !"none".equalsIgnoreCase(commentModel.getAuthorAssociation())) {
      owner.setText(commentModel.getAuthorAssociation().toLowerCase());
      owner.setVisibility(View.VISIBLE);
    }
 else {
      boolean isRepoOwner=TextUtils.equals(commentModel.getUser().getLogin(),repoOwner);
      if (isRepoOwner) {
        owner.setVisibility(View.VISIBLE);
        owner.setText(R.string.owner);
      }
 else {
        boolean isPoster=TextUtils.equals(commentModel.getUser().getLogin(),poster);
        if (isPoster) {
          owner.setVisibility(View.VISIBLE);
          owner.setText(R.string.original_poster);
        }
 else {
          owner.setText("");
          owner.setVisibility(View.GONE);
        }
      }
    }
  }
  date.setText(ParseDateFormat.getTimeAgo(commentModel.getCreatedAt()));
  if (!InputHelper.isEmpty(commentModel.getBodyHtml())) {
    int width=adapter != null ? adapter.getRowWidth() : 0;
    HtmlHelper.htmlIntoTextView(comment,commentModel.getBodyHtml(),width > 0 ? width : viewGroup.getWidth());
  }
 else {
    comment.setText("");
  }
  if (commentModel.getReactions() != null) {
    ReactionsModel reaction=commentModel.getReactions();
    appendEmojies(reaction);
  }
  if (onToggleView != null)   onToggle(onToggleView.isCollapsed(getId()),false);
}
