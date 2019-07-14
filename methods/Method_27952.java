private void hideShowFab(){
  if (pager.getCurrentItem() == 1) {
    getSupportFragmentManager().beginTransaction().show(commentEditorFragment).commit();
  }
 else {
    getSupportFragmentManager().beginTransaction().hide(commentEditorFragment).commit();
  }
}
