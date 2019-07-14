private void showUnblockAlert(int uid){
  if (getParentActivity() == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  CharSequence[] items;
  if (blockedUsersActivity) {
    items=new CharSequence[]{LocaleController.getString("Unblock",R.string.Unblock)};
  }
 else {
    items=new CharSequence[]{LocaleController.getString("Delete",R.string.Delete)};
  }
  builder.setItems(items,(dialogInterface,i) -> {
    if (i == 0) {
      if (blockedUsersActivity) {
        getMessagesController().unblockUser(uid);
      }
 else {
        uidArray.remove((Integer)uid);
        updateRows();
        if (delegate != null) {
          delegate.didUpdateUserList(uidArray,false);
        }
        if (uidArray.isEmpty()) {
          finishFragment();
        }
      }
    }
  }
);
  showDialog(builder.create());
}
