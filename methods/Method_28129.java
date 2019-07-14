@Override public void onShowIssueMisc(){
  TransitionManager.beginDelayedTransition(findViewById(R.id.parent));
  issueMiscLayout.setVisibility(getPresenter().isCollaborator() ? View.VISIBLE : View.GONE);
}
