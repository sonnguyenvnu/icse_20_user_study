@Override public void showReactionsPopup(@NonNull ReactionTypes type,@NonNull String login,@NonNull String repoId,long idOrNumber,int reactionType){
  ReactionsDialogFragment.newInstance(login,repoId,type,idOrNumber,reactionType).show(getChildFragmentManager(),"ReactionsDialogFragment");
}
