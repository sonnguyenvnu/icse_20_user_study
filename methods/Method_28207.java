private Observable<List<Issue>> grabMoreIssues(@NonNull List<Issue> issues,@NonNull String state,@NonNull String sortBy,int page){
  return RestProvider.getIssueService(isEnterprise()).getRepositoryIssues(login,repoId,state,sortBy,page).flatMap(issuePageable -> {
    if (issuePageable != null) {
      lastPage=issuePageable.getLast();
      List<Issue> filtered=Stream.of(issuePageable.getItems()).filter(issue -> issue.getPullRequest() == null).toList();
      if (filtered != null) {
        issues.addAll(filtered);
        if (issues.size() < 10 && issuePageable.getNext() > 1 && this.issues.size() < 10) {
          setCurrentPage(getCurrentPage() + 1);
          return grabMoreIssues(issues,state,sortBy,getCurrentPage());
        }
        issues.addAll(filtered);
        return Observable.just(issues);
      }
    }
    return Observable.just(issues);
  }
);
}
