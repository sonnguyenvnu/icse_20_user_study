@Override public void bind(@NonNull Issue issueModel){
  title.setText(issueModel.getTitle());
  if (issueModel.getState() != null) {
    CharSequence data=ParseDateFormat.getTimeAgo(issueModel.getState() == IssueState.open ? issueModel.getCreatedAt() : issueModel.getClosedAt());
    SpannableBuilder builder=SpannableBuilder.builder();
    if (showRepoName) {
      PullsIssuesParser parser=PullsIssuesParser.getForIssue(issueModel.getHtmlUrl());
      if (parser != null)       builder.bold(parser.getLogin()).append("/").bold(parser.getRepoId()).bold("#").bold(String.valueOf(issueModel.getNumber())).append(" ").append(" ");
    }
    if (!showRepoName) {
      if (issueModel.getState() == IssueState.closed) {
        if (issueModel.getClosedBy() == null) {
          builder.bold("#").bold(String.valueOf(issueModel.getNumber())).append(" ").append(" ");
        }
 else {
          builder.append("#").append(String.valueOf(issueModel.getNumber())).append(" ").append(issueModel.getClosedBy().getLogin()).append(" ");
        }
      }
 else {
        builder.bold("#").bold(String.valueOf(issueModel.getNumber())).append(" ").append(issueModel.getUser().getLogin()).append(" ");
      }
    }
    details.setText(builder.append(itemView.getResources().getString(issueModel.getState().getStatus()).toLowerCase()).append(" ").append(data));
    if (issueModel.getComments() > 0) {
      commentsNo.setText(String.valueOf(issueModel.getComments()));
      commentsNo.setVisibility(View.VISIBLE);
    }
 else {
      commentsNo.setVisibility(View.GONE);
    }
  }
  if (showState) {
    issueState.setVisibility(View.VISIBLE);
    issueState.setImageResource(issueModel.getState() == IssueState.open ? R.drawable.ic_issue_opened_small : R.drawable.ic_issue_closed_small);
  }
 else {
    issueState.setVisibility(View.GONE);
  }
  if (withAvatar && avatarLayout != null) {
    avatarLayout.setUrl(issueModel.getUser().getAvatarUrl(),issueModel.getUser().getLogin(),false,LinkParserHelper.isEnterprise(issueModel.getUser().getHtmlUrl()));
    avatarLayout.setVisibility(View.VISIBLE);
  }
}
