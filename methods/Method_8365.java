private boolean checkDiscard(){
  String newBannedRights=ChatObject.getBannedRightsString(defaultBannedRights);
  if (!newBannedRights.equals(initialBannedRights)) {
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    builder.setTitle(LocaleController.getString("UserRestrictionsApplyChanges",R.string.UserRestrictionsApplyChanges));
    if (isChannel) {
      builder.setMessage(LocaleController.getString("ChannelSettingsChangedAlert",R.string.ChannelSettingsChangedAlert));
    }
 else {
      builder.setMessage(LocaleController.getString("GroupSettingsChangedAlert",R.string.GroupSettingsChangedAlert));
    }
    builder.setPositiveButton(LocaleController.getString("ApplyTheme",R.string.ApplyTheme),(dialogInterface,i) -> processDone());
    builder.setNegativeButton(LocaleController.getString("PassportDiscard",R.string.PassportDiscard),(dialog,which) -> finishFragment());
    showDialog(builder.create());
    return false;
  }
  return true;
}
