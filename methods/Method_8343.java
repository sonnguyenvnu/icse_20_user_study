private void loadAdminedChannels(){
  if (loadingAdminedChannels || adminnedChannelsLayout == null) {
    return;
  }
  loadingAdminedChannels=true;
  updatePrivatePublic();
  TLRPC.TL_channels_getAdminedPublicChannels req=new TLRPC.TL_channels_getAdminedPublicChannels();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    loadingAdminedChannels=false;
    if (response != null) {
      if (getParentActivity() == null) {
        return;
      }
      for (int a=0; a < adminedChannelCells.size(); a++) {
        linearLayout.removeView(adminedChannelCells.get(a));
      }
      adminedChannelCells.clear();
      TLRPC.TL_messages_chats res=(TLRPC.TL_messages_chats)response;
      for (int a=0; a < res.chats.size(); a++) {
        AdminedChannelCell adminedChannelCell=new AdminedChannelCell(getParentActivity(),view -> {
          AdminedChannelCell cell=(AdminedChannelCell)view.getParent();
          final TLRPC.Chat channel=cell.getCurrentChannel();
          AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
          builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
          if (isChannel) {
            builder.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("RevokeLinkAlertChannel",R.string.RevokeLinkAlertChannel,MessagesController.getInstance(currentAccount).linkPrefix + "/" + channel.username,channel.title)));
          }
 else {
            builder.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("RevokeLinkAlert",R.string.RevokeLinkAlert,MessagesController.getInstance(currentAccount).linkPrefix + "/" + channel.username,channel.title)));
          }
          builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
          builder.setPositiveButton(LocaleController.getString("RevokeButton",R.string.RevokeButton),(dialogInterface,i) -> {
            TLRPC.TL_channels_updateUsername req1=new TLRPC.TL_channels_updateUsername();
            req1.channel=MessagesController.getInputChannel(channel);
            req1.username="";
            ConnectionsManager.getInstance(currentAccount).sendRequest(req1,(response1,error1) -> {
              if (response1 instanceof TLRPC.TL_boolTrue) {
                AndroidUtilities.runOnUIThread(() -> {
                  canCreatePublic=true;
                  if (usernameTextView.length() > 0) {
                    checkUserName(usernameTextView.getText().toString());
                  }
                  updatePrivatePublic();
                }
);
              }
            }
,ConnectionsManager.RequestFlagInvokeAfter);
          }
);
          showDialog(builder.create());
        }
);
        adminedChannelCell.setChannel(res.chats.get(a),a == res.chats.size() - 1);
        adminedChannelCells.add(adminedChannelCell);
        adminnedChannelsLayout.addView(adminedChannelCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,72));
      }
      updatePrivatePublic();
    }
  }
));
}
