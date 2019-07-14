private boolean createMenuForParticipant(final TLObject participant,boolean resultOnly){
  if (participant == null || selectType != 0) {
    return false;
  }
  int userId;
  boolean canEdit;
  int date;
  TLRPC.TL_chatBannedRights bannedRights;
  TLRPC.TL_chatAdminRights adminRights;
  if (participant instanceof TLRPC.ChannelParticipant) {
    TLRPC.ChannelParticipant channelParticipant=(TLRPC.ChannelParticipant)participant;
    userId=channelParticipant.user_id;
    canEdit=channelParticipant.can_edit;
    bannedRights=channelParticipant.banned_rights;
    adminRights=channelParticipant.admin_rights;
    date=channelParticipant.date;
  }
 else   if (participant instanceof TLRPC.ChatParticipant) {
    TLRPC.ChatParticipant chatParticipant=(TLRPC.ChatParticipant)participant;
    userId=chatParticipant.user_id;
    date=chatParticipant.date;
    canEdit=ChatObject.canAddAdmins(currentChat);
    bannedRights=null;
    adminRights=null;
  }
 else {
    userId=0;
    canEdit=false;
    bannedRights=null;
    adminRights=null;
    date=0;
  }
  if (userId == 0 || userId == UserConfig.getInstance(currentAccount).getClientUserId()) {
    return false;
  }
  if (type == TYPE_USERS) {
    final TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(userId);
    boolean allowSetAdmin=ChatObject.canAddAdmins(currentChat) && (participant instanceof TLRPC.TL_channelParticipant || participant instanceof TLRPC.TL_channelParticipantBanned || participant instanceof TLRPC.TL_chatParticipant || canEdit);
    boolean canEditAdmin=!(participant instanceof TLRPC.TL_channelParticipantAdmin || participant instanceof TLRPC.TL_channelParticipantCreator || participant instanceof TLRPC.TL_chatParticipantCreator || participant instanceof TLRPC.TL_chatParticipantAdmin) || canEdit;
    boolean editingAdmin=participant instanceof TLRPC.TL_channelParticipantAdmin || participant instanceof TLRPC.TL_chatParticipantAdmin;
    final ArrayList<String> items;
    final ArrayList<Integer> actions;
    final ArrayList<Integer> icons;
    if (!resultOnly) {
      items=new ArrayList<>();
      actions=new ArrayList<>();
      icons=new ArrayList<>();
    }
 else {
      items=null;
      actions=null;
      icons=null;
    }
    if (allowSetAdmin) {
      if (resultOnly) {
        return true;
      }
      items.add(editingAdmin ? LocaleController.getString("EditAdminRights",R.string.EditAdminRights) : LocaleController.getString("SetAsAdmin",R.string.SetAsAdmin));
      icons.add(R.drawable.actions_addadmin);
      actions.add(0);
    }
    boolean hasRemove=false;
    if (ChatObject.canBlockUsers(currentChat) && canEditAdmin) {
      if (resultOnly) {
        return true;
      }
      if (!isChannel) {
        if (ChatObject.isChannel(currentChat)) {
          items.add(LocaleController.getString("ChangePermissions",R.string.ChangePermissions));
          icons.add(R.drawable.actions_permissions);
          actions.add(1);
        }
        items.add(LocaleController.getString("KickFromGroup",R.string.KickFromGroup));
        icons.add(R.drawable.actions_remove_user);
        actions.add(2);
      }
 else {
        items.add(LocaleController.getString("ChannelRemoveUser",R.string.ChannelRemoveUser));
        icons.add(R.drawable.actions_remove_user);
        actions.add(2);
      }
      hasRemove=true;
    }
    if (actions == null || actions.isEmpty()) {
      return false;
    }
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    builder.setItems(items.toArray(new CharSequence[actions.size()]),AndroidUtilities.toIntArray(icons),(dialogInterface,i) -> {
      if (actions.get(i) == 2) {
        MessagesController.getInstance(currentAccount).deleteUserFromChat(chatId,user,null);
        removeParticipants(userId);
        if (searchItem != null && actionBar.isSearchFieldVisible()) {
          actionBar.closeSearchField();
        }
      }
 else {
        if (canEditAdmin && (participant instanceof TLRPC.TL_channelParticipantAdmin || participant instanceof TLRPC.TL_chatParticipantAdmin)) {
          AlertDialog.Builder builder2=new AlertDialog.Builder(getParentActivity());
          builder2.setTitle(LocaleController.getString("AppName",R.string.AppName));
          builder2.setMessage(LocaleController.formatString("AdminWillBeRemoved",R.string.AdminWillBeRemoved,ContactsController.formatName(user.first_name,user.last_name)));
          builder2.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialog,which) -> openRightsEdit2(userId,date,participant,adminRights,bannedRights,canEditAdmin,actions.get(i),false));
          builder2.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
          showDialog(builder2.create());
        }
 else {
          openRightsEdit2(userId,date,participant,adminRights,bannedRights,canEditAdmin,actions.get(i),false);
        }
      }
    }
);
    AlertDialog alertDialog=builder.create();
    showDialog(alertDialog);
    if (hasRemove) {
      alertDialog.setItemColor(items.size() - 1,Theme.getColor(Theme.key_dialogTextRed2),Theme.getColor(Theme.key_dialogRedIcon));
    }
  }
 else {
    CharSequence[] items;
    int[] icons;
    if (type == TYPE_KICKED && ChatObject.canBlockUsers(currentChat)) {
      if (resultOnly) {
        return true;
      }
      items=new CharSequence[]{LocaleController.getString("ChannelEditPermissions",R.string.ChannelEditPermissions),LocaleController.getString("ChannelDeleteFromList",R.string.ChannelDeleteFromList)};
      icons=new int[]{R.drawable.actions_permissions,R.drawable.chats_delete};
    }
 else     if (type == TYPE_BANNED && ChatObject.canBlockUsers(currentChat)) {
      if (resultOnly) {
        return true;
      }
      items=new CharSequence[]{ChatObject.canAddUsers(currentChat) ? (isChannel ? LocaleController.getString("ChannelAddToChannel",R.string.ChannelAddToChannel) : LocaleController.getString("ChannelAddToGroup",R.string.ChannelAddToGroup)) : null,LocaleController.getString("ChannelDeleteFromList",R.string.ChannelDeleteFromList)};
      icons=new int[]{R.drawable.actions_addmember2,R.drawable.chats_delete};
    }
 else     if (type == TYPE_ADMIN && ChatObject.canAddAdmins(currentChat) && canEdit) {
      if (resultOnly) {
        return true;
      }
      if (currentChat.creator || !(participant instanceof TLRPC.TL_channelParticipantCreator) && canEdit) {
        items=new CharSequence[]{LocaleController.getString("EditAdminRights",R.string.EditAdminRights),LocaleController.getString("ChannelRemoveUserAdmin",R.string.ChannelRemoveUserAdmin)};
        icons=new int[]{R.drawable.actions_addadmin,R.drawable.actions_remove_user};
      }
 else {
        items=new CharSequence[]{LocaleController.getString("ChannelRemoveUserAdmin",R.string.ChannelRemoveUserAdmin)};
        icons=new int[]{R.drawable.actions_remove_user};
      }
    }
 else {
      items=null;
      icons=null;
    }
    if (items == null) {
      return false;
    }
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    builder.setItems(items,icons,(dialogInterface,i) -> {
      if (type == TYPE_ADMIN) {
        if (i == 0 && items.length == 2) {
          ChatRightsEditActivity fragment=new ChatRightsEditActivity(userId,chatId,adminRights,null,null,ChatRightsEditActivity.TYPE_ADMIN,true,false);
          fragment.setDelegate((rights,rightsAdmin,rightsBanned) -> {
            if (participant instanceof TLRPC.ChannelParticipant) {
              TLRPC.ChannelParticipant channelParticipant=(TLRPC.ChannelParticipant)participant;
              channelParticipant.admin_rights=rightsAdmin;
              channelParticipant.banned_rights=rightsBanned;
              updateParticipantWithRights(channelParticipant,rightsAdmin,rightsBanned,0,false);
            }
          }
);
          presentFragment(fragment);
        }
 else {
          MessagesController.getInstance(currentAccount).setUserAdminRole(chatId,MessagesController.getInstance(currentAccount).getUser(userId),new TLRPC.TL_chatAdminRights(),!isChannel,ChatUsersActivity.this,false);
          removeParticipants(userId);
        }
      }
 else       if (type == TYPE_BANNED || type == TYPE_KICKED) {
        if (i == 0) {
          if (type == TYPE_KICKED) {
            ChatRightsEditActivity fragment=new ChatRightsEditActivity(userId,chatId,null,defaultBannedRights,bannedRights,ChatRightsEditActivity.TYPE_BANNED,true,false);
            fragment.setDelegate((rights,rightsAdmin,rightsBanned) -> {
              if (participant instanceof TLRPC.ChannelParticipant) {
                TLRPC.ChannelParticipant channelParticipant=(TLRPC.ChannelParticipant)participant;
                channelParticipant.admin_rights=rightsAdmin;
                channelParticipant.banned_rights=rightsBanned;
                updateParticipantWithRights(channelParticipant,rightsAdmin,rightsBanned,0,false);
              }
            }
);
            presentFragment(fragment);
          }
 else           if (type == TYPE_BANNED) {
            TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(userId);
            MessagesController.getInstance(currentAccount).addUserToChat(chatId,user,null,0,null,ChatUsersActivity.this,null);
          }
        }
 else         if (i == 1) {
          TLRPC.TL_channels_editBanned req=new TLRPC.TL_channels_editBanned();
          req.user_id=MessagesController.getInstance(currentAccount).getInputUser(userId);
          req.channel=MessagesController.getInstance(currentAccount).getInputChannel(chatId);
          req.banned_rights=new TLRPC.TL_chatBannedRights();
          ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
            if (response != null) {
              final TLRPC.Updates updates=(TLRPC.Updates)response;
              MessagesController.getInstance(currentAccount).processUpdates(updates,false);
              if (!updates.chats.isEmpty()) {
                AndroidUtilities.runOnUIThread(() -> {
                  TLRPC.Chat chat=updates.chats.get(0);
                  MessagesController.getInstance(currentAccount).loadFullChat(chat.id,0,true);
                }
,1000);
              }
            }
          }
);
          if (searchItem != null && actionBar.isSearchFieldVisible()) {
            actionBar.closeSearchField();
          }
        }
        if (i == 0 && type == TYPE_BANNED || i == 1) {
          removeParticipants(participant);
        }
      }
 else {
        if (i == 0) {
          MessagesController.getInstance(currentAccount).deleteUserFromChat(chatId,MessagesController.getInstance(currentAccount).getUser(userId),null);
        }
      }
    }
);
    AlertDialog alertDialog=builder.create();
    showDialog(alertDialog);
    if (type == TYPE_ADMIN) {
      alertDialog.setItemColor(items.length - 1,Theme.getColor(Theme.key_dialogTextRed2),Theme.getColor(Theme.key_dialogRedIcon));
    }
  }
  return true;
}
