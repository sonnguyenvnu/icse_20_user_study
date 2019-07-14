private void showHideFab(){
  if (navType == RepoPagerMvp.ISSUES) {
    fab.setImageResource(R.drawable.ic_menu);
    fab.show();
    if (!PrefGetter.isRepoFabHintShowed()) {
    }
  }
 else   if (navType == RepoPagerMvp.PULL_REQUEST) {
    fab.setImageResource(R.drawable.ic_search);
    fab.show();
  }
 else {
    fab.hide();
  }
}
