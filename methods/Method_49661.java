@Override public boolean onMenuItemClick(MenuItem item){
  int itemId=item.getItemId();
  String code=mView.getCodeAndLocality();
  if (itemId == R.id.share_code) {
    openShareChooser(code);
    return true;
  }
 else   if (itemId == R.id.save_to_contact) {
    saveCodeAsContact(code);
    return true;
  }
  return false;
}
