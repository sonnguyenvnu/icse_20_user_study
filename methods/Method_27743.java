@NonNull private String getUrl(@NonNull IssueState parameter){
switch (issuesType) {
case CREATED:
    return RepoQueryProvider.getMyIssuesPullRequestQuery(login,parameter,true);
case ASSIGNED:
  return RepoQueryProvider.getAssigned(login,parameter,true);
case MENTIONED:
return RepoQueryProvider.getMentioned(login,parameter,true);
case REVIEW:
return RepoQueryProvider.getReviewRequests(login,parameter);
}
return RepoQueryProvider.getMyIssuesPullRequestQuery(login,parameter,false);
}
