private boolean checkDiscard(){
  if (doneButton.getVisibility() == View.VISIBLE) {
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    builder.setTitle(LocaleController.getString("UserRestrictionsApplyChanges",R.string.UserRestrictionsApplyChanges));
    builder.setMessage(LocaleController.getString("PrivacySettingsChangedAlert",R.string.PrivacySettingsChangedAlert));
    builder.setPositiveButton(LocaleController.getString("ApplyTheme",R.string.ApplyTheme),(dialogInterface,i) -> processDone());
    builder.setNegativeButton(LocaleController.getString("PassportDiscard",R.string.PassportDiscard),(dialog,which) -> finishFragment());
    showDialog(builder.create());
    return false;
  }
  return true;
}
