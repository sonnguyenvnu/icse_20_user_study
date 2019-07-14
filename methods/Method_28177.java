@Override public void showReactionsPopup(@NonNull ReactionTypes type,@NonNull String login,@NonNull String repoId,long idOrNumber,boolean isHeader){
  ReactionsDialogFragment.newInstance(login,repoId,type,idOrNumber,isHeader ? ReactionsProvider.HEADER : ReactionsProvider.COMMENT).show(getChildFragmentManager(),"ReactionsDialogFragment");
}
