@Override public boolean onCallApi(int page,@Nullable Object parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  if (page > lastPage || lastPage == 0 || (login == null || repoId == null || reactionType == null)) {
    sendToView(ReactionsDialogMvp.View::hideProgress);
    return false;
  }
  setCurrentPage(page);
  Observable<Pageable<ReactionsModel>> observable=null;
switch (reactionTypeMode) {
case ReactionsProvider.COMMENT:
    observable=RestProvider.getReactionsService(isEnterprise()).getIssueCommentReaction(login,repoId,id,reactionType.getContent(),page);
  break;
case ReactionsProvider.COMMIT:
observable=RestProvider.getReactionsService(isEnterprise()).getCommitReaction(login,repoId,id,reactionType.getContent(),page);
break;
case ReactionsProvider.HEADER:
observable=RestProvider.getReactionsService(isEnterprise()).getIssueReaction(login,repoId,id,reactionType.getContent(),page);
break;
case ReactionsProvider.REVIEW_COMMENT:
observable=RestProvider.getReactionsService(isEnterprise()).getPullRequestReactions(login,repoId,id,reactionType.getContent(),page);
break;
}
if (observable == null) {
throw new NullPointerException("Reaction is null?");
}
makeRestCall(observable,response -> {
lastPage=response.getLast();
sendToView(view -> view.onNotifyAdapter(Stream.of(response.getItems()).filter(reactionsModel -> reactionsModel.getUser() != null).map(ReactionsModel::getUser).collect(Collectors.toList()),page));
}
);
return true;
}
