private boolean checkDiscard(){
  if (isHasNotAnyChanges()) {
    return false;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setPositiveButton(LocaleController.getString("PassportDiscard",R.string.PassportDiscard),(dialog,which) -> finishFragment());
  builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  builder.setTitle(LocaleController.getString("DiscardChanges",R.string.DiscardChanges));
  builder.setMessage(LocaleController.getString("PassportDiscardChanges",R.string.PassportDiscardChanges));
  showDialog(builder.create());
  return true;
}
