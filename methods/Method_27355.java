private void postCommit(@NonNull ReactionTypes reactionType,@NonNull String login,@NonNull String repo,long commentId,boolean isEnterprise){
  RxHelper.safeObservable(RestProvider.getReactionsService(isEnterprise).postCommitReaction(new PostReactionModel(reactionType.getContent()),login,repo,commentId)).doOnSubscribe(disposable -> showNotification(getNotification(reactionType),(int)commentId)).subscribe(response -> hideNotification((int)commentId),throwable -> hideNotification((int)commentId));
}
