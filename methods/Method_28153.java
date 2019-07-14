private void updateViews(@NonNull Issue issueModel){
  User userModel=issueModel.getUser();
  title.setText(issueModel.getTitle());
  detailsIcon.setVisibility(View.VISIBLE);
  if (userModel != null) {
    size.setVisibility(View.GONE);
    String username;
    CharSequence parsedDate;
    if (issueModel.getState() == IssueState.closed) {
      username=issueModel.getClosedBy() != null ? issueModel.getClosedBy().getLogin() : "N/A";
      parsedDate=issueModel.getClosedAt() != null ? ParseDateFormat.getTimeAgo(issueModel.getClosedAt()) : "N/A";
    }
 else {
      parsedDate=ParseDateFormat.getTimeAgo(issueModel.getCreatedAt());
      username=issueModel.getUser() != null ? issueModel.getUser().getLogin() : "N/A";
    }
    date.setText(SpannableBuilder.builder().append(ContextCompat.getDrawable(this,issueModel.getState() == IssueState.open ? R.drawable.ic_issue_opened_small : R.drawable.ic_issue_closed_small)).append(" ").append(getString(issueModel.getState().getStatus())).append(" ").append(getString(R.string.by)).append(" ").append(username).append(" ").append(parsedDate));
    avatarLayout.setUrl(userModel.getAvatarUrl(),userModel.getLogin(),false,LinkParserHelper.isEnterprise(issueModel.getHtmlUrl()));
  }
}
