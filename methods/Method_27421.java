@Nullable public Observable onHandleReaction(@IdRes int viewId,long idOrNumber,@Nullable String login,@Nullable String repoId,@ReactionType int reactionType,boolean isEnterprise){
  if (!InputHelper.isEmpty(login) && !InputHelper.isEmpty(repoId)) {
    if (!isPreviouslyReacted(idOrNumber,viewId)) {
      ReactionTypes reactionTypes=ReactionTypes.get(viewId);
      if (reactionTypes != null) {
        Observable<ReactionsModel> observable=null;
switch (reactionType) {
case COMMENT:
          observable=RestProvider.getReactionsService(isEnterprise).postIssueCommentReaction(new PostReactionModel(reactionTypes.getContent()),login,repoId,idOrNumber);
        break;
case HEADER:
      observable=RestProvider.getReactionsService(isEnterprise).postIssueReaction(new PostReactionModel(reactionTypes.getContent()),login,repoId,idOrNumber);
    break;
case REVIEW_COMMENT:
  observable=RestProvider.getReactionsService(isEnterprise).postCommentReviewReaction(new PostReactionModel(reactionTypes.getContent()),login,repoId,idOrNumber);
break;
case COMMIT:
observable=RestProvider.getReactionsService(isEnterprise).postCommitReaction(new PostReactionModel(reactionTypes.getContent()),login,repoId,idOrNumber);
break;
}
if (observable == null) return null;
return RxHelper.safeObservable(observable).doOnNext(response -> getReactionsMap().put(idOrNumber,response));
}
}
 else {
ReactionsModel reactionsModel=getReactionsMap().get(idOrNumber);
if (reactionsModel != null) {
return RxHelper.safeObservable(RestProvider.getReactionsService(isEnterprise).delete(reactionsModel.getId())).doOnNext(booleanResponse -> {
if (booleanResponse.code() == 204) {
getReactionsMap().remove(idOrNumber);
}
}
);
}
}
}
return null;
}
