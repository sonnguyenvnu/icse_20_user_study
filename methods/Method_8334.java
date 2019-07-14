private boolean checkDiscard(){
  String about=info != null && info.about != null ? info.about : "";
  if (info != null && ChatObject.isChannel(currentChat) && info.hidden_prehistory != historyHidden || imageUpdater.uploadingImage != null || nameTextView != null && !currentChat.title.equals(nameTextView.getText().toString()) || descriptionTextView != null && !about.equals(descriptionTextView.getText().toString()) || signMessages != currentChat.signatures || uploadedAvatar != null || avatar == null && currentChat.photo instanceof TLRPC.TL_chatPhoto) {
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
