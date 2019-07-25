@Override public void apply() throws ConfigurationException {
  settings.getState().NOTIFY_MSG=chkNotify.isSelected();
  settings.getState().NOTIFY_UNREAD=chkNotifyUnread.isSelected();
  settings.getState().SHOW_SEND=chkSendBtn.isSelected();
  settings.getState().NOTIFY_GROUP_MSG=chkNotifyGroupMsg.isSelected();
  settings.getState().NOTIFY_UNKNOWN=chkNotifyUnknown.isSelected();
  settings.getState().HIDE_MY_INPUT=chkHideMyInput.isSelected();
  settings.getState().LOG_HISTORY=chkHistory.isSelected();
  settings.getState().KEY_SEND=comboSend.getSelectedItem().toString();
}
