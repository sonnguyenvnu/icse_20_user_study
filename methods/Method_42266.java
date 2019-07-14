private void setupListeners(){
  OnClickListener onClickListener=v -> {
    if (itemListener == null)     return;
    itemListener.onItemSelected(getNavigationItemSelected(v.getId()));
  }
;
  for (  NavigationEntry navigationEntry : navigationEntries) {
    navigationEntry.setOnClickListener(onClickListener);
  }
}
