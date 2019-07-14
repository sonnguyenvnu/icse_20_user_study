private void hideShowFab(int position){
  if (isOrg) {
    if (getPresenter().getIsMember() == 1) {
      if (position == 2) {
        fab.show();
      }
 else {
        fab.hide();
      }
    }
 else {
      if (position == 1) {
        fab.show();
      }
 else {
        fab.hide();
      }
    }
  }
 else {
    if (position == 2) {
      fab.show();
    }
 else {
      fab.hide();
    }
  }
}
