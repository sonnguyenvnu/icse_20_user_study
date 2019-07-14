@Override public void showReactionsPopup(@NonNull ReactionTypes reactionTypes,@NonNull String login,@NonNull String repoId,long commentId){
  ReactionsDialogFragment.newInstance(login,repoId,reactionTypes,commentId,ReactionsProvider.COMMIT).show(getChildFragmentManager(),"ReactionsDialogFragment");
}
