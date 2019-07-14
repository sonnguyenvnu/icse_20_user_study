@Override public void onDragBottom(boolean rightToLeft){
  if (isAlive() == false) {
    return;
  }
  if (rightToLeft == false) {
    startActivity(MomentListActivity.createIntent(context,RANGE_ALL,0));
    context.overridePendingTransition(R.anim.bottom_push_in,R.anim.hold);
  }
 else {
    if (range != RANGE_ALL && verifyLogin() == false) {
      return;
    }
    if (isAdd) {
      toActivity(EditTextInfoWindow.createIntent(context,EditTextInfoWindow.TYPE_NOTE,"???","?????~"),REQUEST_TO_EDIT_TEXT_INFO,false);
    }
 else {
      showShortToast("?????????");
      toActivity(EditTextInfoWindow.createIntent(context,EditTextInfoWindow.TYPE_NAME,"???",null),REQUEST_TO_EDIT_TEXT_INFO,false);
    }
  }
}
