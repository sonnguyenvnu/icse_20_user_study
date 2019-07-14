private void onHandleReaction(int viewId,long id){
  Observable observable=getReactionsProvider().onHandleReaction(viewId,id,login,repoId,ReactionsProvider.COMMIT,isEnterprise());
  if (observable != null)   manageObservable(observable);
}
