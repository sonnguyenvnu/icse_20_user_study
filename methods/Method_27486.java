@Override public void bind(@NonNull TimelineModel timelineModel){
  Comment commentsModel=timelineModel.getComment();
  if (commentsModel.getUser() != null) {
    avatar.setUrl(commentsModel.getUser().getAvatarUrl(),commentsModel.getUser().getLogin(),false,LinkParserHelper.isEnterprise(commentsModel.getHtmlUrl()));
    name.setText(commentsModel.getUser() != null ? commentsModel.getUser().getLogin() : "Anonymous");
    if (commentsModel.getAuthorAssociation() != null && !"none".equalsIgnoreCase(commentsModel.getAuthorAssociation())) {
      owner.setText(commentsModel.getAuthorAssociation().toLowerCase());
      owner.setVisibility(View.VISIBLE);
    }
 else {
      boolean isRepoOwner=TextUtils.equals(commentsModel.getUser().getLogin(),repoOwner);
      if (isRepoOwner) {
        owner.setVisibility(View.VISIBLE);
        owner.setText(R.string.owner);
      }
 else {
        boolean isPoster=TextUtils.equals(commentsModel.getUser().getLogin(),poster);
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
 else {
    avatar.setUrl(null,null,false,false);
    name.setText("");
  }
  if (!InputHelper.isEmpty(commentsModel.getPath()) && commentsModel.getPosition() > 0) {
    pathText.setVisibility(View.VISIBLE);
    pathText.setText(String.format("Commented on %s#L%s",commentsModel.getPath(),commentsModel.getLine() > 0 ? commentsModel.getLine() : commentsModel.getPosition()));
  }
 else {
    pathText.setText("");
    pathText.setVisibility(View.GONE);
  }
  if (!InputHelper.isEmpty(commentsModel.getBodyHtml())) {
    String body=commentsModel.getBodyHtml();
    int width=adapter != null ? adapter.getRowWidth() : 0;
    HtmlHelper.htmlIntoTextView(comment,body,width > 0 ? width : viewGroup.getWidth());
  }
 else {
    comment.setText("");
  }
  if (commentsModel.getCreatedAt().before(commentsModel.getUpdatedAt())) {
    date.setText(String.format("%s %s",ParseDateFormat.getTimeAgo(commentsModel.getCreatedAt()),itemView.getResources().getString(R.string.edited)));
  }
 else {
    date.setText(ParseDateFormat.getTimeAgo(commentsModel.getCreatedAt()));
  }
  if (showEmojies) {
    if (commentsModel.getReactions() != null) {
      ReactionsModel reaction=commentsModel.getReactions();
      appendEmojies(reaction);
    }
  }
  emojiesList.setVisibility(showEmojies ? View.VISIBLE : View.GONE);
  if (onToggleView != null)   onToggle(onToggleView.isCollapsed(getAdapterPosition()),false);
}
