private void hideShowFab(){
  if (getPresenter().isLocked() && !getPresenter().isOwner()) {
    getSupportFragmentManager().beginTransaction().hide(commentEditorFragment).commit();
    return;
  }
  getSupportFragmentManager().beginTransaction().show(commentEditorFragment).commit();
}
