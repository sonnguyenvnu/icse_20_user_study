@NonNull public static SpannableBuilder getMergeBy(@NonNull PullRequest pullRequest,@NonNull Context context,boolean showRepoName){
  boolean isMerge=pullRequest.isMerged() || !InputHelper.isEmpty(pullRequest.mergedAt);
  if (isMerge) {
    User merger=pullRequest.getMergedBy();
    SpannableBuilder builder=SpannableBuilder.builder();
    if (showRepoName) {
      PullsIssuesParser parser=PullsIssuesParser.getForPullRequest(pullRequest.getHtmlUrl());
      if (parser != null)       builder.bold(parser.getLogin()).append("/").bold(parser.getRepoId()).append(" ").bold("#").bold(String.valueOf(pullRequest.getNumber())).append(" ");
    }
 else {
      builder.bold("#" + pullRequest.getNumber()).append(" ").append(merger != null ? merger.getLogin() + " " : "");
    }
    builder.append(context.getString(R.string.merged).toLowerCase()).append(" ");
    if (pullRequest.getHead() != null) {
      builder.bold(pullRequest.getHead().getRef()).append(" ").append(context.getString(R.string.to)).append(" ").bold(pullRequest.getBase().getRef()).append(" ");
    }
    builder.append(ParseDateFormat.getTimeAgo(pullRequest.getMergedAt()));
    return builder;
  }
 else {
    User user=pullRequest.getUser();
    String status=context.getString(pullRequest.getState().getStatus());
    SpannableBuilder builder=SpannableBuilder.builder();
    if (showRepoName) {
      PullsIssuesParser parser=PullsIssuesParser.getForPullRequest(pullRequest.getHtmlUrl());
      if (parser != null) {
        builder.bold(parser.getLogin()).append("/").bold(parser.getRepoId()).append(" ").bold("#").bold(String.valueOf(pullRequest.getNumber())).append(" ");
      }
    }
 else {
      builder.bold("#" + pullRequest.getNumber()).append(" ").append(user.getLogin()).append(" ");
    }
    if (pullRequest.getState() == IssueState.open && pullRequest.getHead() != null && pullRequest.getBase() != null) {
      return builder.append(context.getString(R.string.want_to_merge)).append(" ").bold(pullRequest.getHead().getRef()).append(" ").append(context.getString(R.string.to)).append(" ").bold(pullRequest.getBase().getRef()).append(" ").append(ParseDateFormat.getTimeAgo(pullRequest.getState() == IssueState.closed ? pullRequest.getClosedAt() : pullRequest.getCreatedAt()));
    }
 else {
      return builder.bold(status.toLowerCase()).append(" ").bold(pullRequest.getHead() != null ? pullRequest.getHead().getRef() : "").append(" ").append(ParseDateFormat.getTimeAgo(pullRequest.getState() == IssueState.closed ? pullRequest.getClosedAt() : pullRequest.getCreatedAt()));
    }
  }
}
