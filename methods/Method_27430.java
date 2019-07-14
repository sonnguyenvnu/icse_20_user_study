@Override public void bind(@NonNull Comment commentsModel){
  if (commentsModel.getUser() != null) {
    avatar.setUrl(commentsModel.getUser().getAvatarUrl(),commentsModel.getUser().getLogin(),commentsModel.getUser().isOrganizationType(),LinkParserHelper.isEnterprise(commentsModel.getUser().getHtmlUrl()));
  }
 else {
    avatar.setUrl(null,null,false,false);
  }
  if (!InputHelper.isEmpty(commentsModel.getBodyHtml())) {
    int width=adapter != null ? adapter.getRowWidth() : 0;
    HtmlHelper.htmlIntoTextView(comment,commentsModel.getBodyHtml(),width > 0 ? width : viewGroup.getWidth());
  }
 else {
    comment.setText("");
  }
  name.setText(commentsModel.getUser() != null ? commentsModel.getUser().getLogin() : "Anonymous");
  if (commentsModel.getCreatedAt().before(commentsModel.getUpdatedAt())) {
    date.setText(String.format("%s %s",ParseDateFormat.getTimeAgo(commentsModel.getCreatedAt()),date.getResources().getString(R.string.edited)));
  }
 else {
    date.setText(ParseDateFormat.getTimeAgo(commentsModel.getCreatedAt()));
  }
}
