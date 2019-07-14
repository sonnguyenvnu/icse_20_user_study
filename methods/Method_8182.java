private void updateEmptyPlaceholder(){
  if (emptyView == null) {
    return;
  }
  if (!TextUtils.isEmpty(searchQuery)) {
    emptyView.setPadding(AndroidUtilities.dp(8),AndroidUtilities.dp(5),AndroidUtilities.dp(8),AndroidUtilities.dp(5));
    emptyView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("EventLogEmptyTextSearch",R.string.EventLogEmptyTextSearch,searchQuery)));
  }
 else   if (selectedAdmins != null || currentFilter != null) {
    emptyView.setPadding(AndroidUtilities.dp(8),AndroidUtilities.dp(5),AndroidUtilities.dp(8),AndroidUtilities.dp(5));
    emptyView.setText(AndroidUtilities.replaceTags(LocaleController.getString("EventLogEmptySearch",R.string.EventLogEmptySearch)));
  }
 else {
    emptyView.setPadding(AndroidUtilities.dp(16),AndroidUtilities.dp(16),AndroidUtilities.dp(16),AndroidUtilities.dp(16));
    if (currentChat.megagroup) {
      emptyView.setText(AndroidUtilities.replaceTags(LocaleController.getString("EventLogEmpty",R.string.EventLogEmpty)));
    }
 else {
      emptyView.setText(AndroidUtilities.replaceTags(LocaleController.getString("EventLogEmptyChannel",R.string.EventLogEmptyChannel)));
    }
  }
}
