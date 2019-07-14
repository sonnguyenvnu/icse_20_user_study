@Override public void onDragBottom(boolean rightToLeft){
  if (isAlive() == false) {
    return;
  }
  if (rightToLeft == false) {
    startActivity(UserListActivity.createIntent(context,RANGE_ALL,0).putExtra(INTENT_TITLE,"??"));
    context.overridePendingTransition(R.anim.bottom_push_in,R.anim.hold);
  }
 else {
    if (range != RANGE_ALL && verifyLogin() == false) {
      return;
    }
    showShortToast("?????????");
    if (searchType <= 0) {
      searchType=EditTextInfoWindow.TYPE_PHONE;
    }
    toActivity(EditTextInfoWindow.createIntent(context,EditTextInfoWindow.TYPE_NAME,"??",null),REQUEST_TO_EDIT_TEXT_INFO_SEARCH,false);
  }
}
