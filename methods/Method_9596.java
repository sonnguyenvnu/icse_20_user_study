private void createActionBarMenu(){
  ActionBarMenu menu=actionBar.createMenu();
  menu.clearItems();
  animatingItem=null;
  ActionBarMenuItem item=null;
  if (user_id != 0) {
    if (UserConfig.getInstance(currentAccount).getClientUserId() != user_id) {
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(user_id);
      if (user == null) {
        return;
      }
      if (userInfo != null && userInfo.phone_calls_available) {
        callItem=menu.addItem(call_item,R.drawable.ic_call);
      }
      if (isBot || ContactsController.getInstance(currentAccount).contactsDict.get(user_id) == null) {
        item=menu.addItem(10,R.drawable.ic_ab_other);
        if (MessagesController.isSupportUser(user)) {
          if (userBlocked) {
            item.addSubItem(block_contact,R.drawable.msg_block,LocaleController.getString("Unblock",R.string.Unblock));
          }
        }
 else {
          if (isBot) {
            if (!user.bot_nochats) {
              item.addSubItem(invite_to_group,R.drawable.msg_addbot,LocaleController.getString("BotInvite",R.string.BotInvite));
            }
            item.addSubItem(share,R.drawable.msg_share,LocaleController.getString("BotShare",R.string.BotShare));
          }
          if (user.phone != null && user.phone.length() != 0) {
            item.addSubItem(add_contact,R.drawable.msg_addcontact,LocaleController.getString("AddContact",R.string.AddContact));
            item.addSubItem(share_contact,R.drawable.msg_share,LocaleController.getString("ShareContact",R.string.ShareContact));
            item.addSubItem(block_contact,!userBlocked ? R.drawable.msg_block : R.drawable.msg_block,!userBlocked ? LocaleController.getString("BlockContact",R.string.BlockContact) : LocaleController.getString("Unblock",R.string.Unblock));
          }
 else {
            if (isBot) {
              item.addSubItem(block_contact,!userBlocked ? R.drawable.msg_block : R.drawable.msg_retry,!userBlocked ? LocaleController.getString("BotStop",R.string.BotStop) : LocaleController.getString("BotRestart",R.string.BotRestart));
            }
 else {
              item.addSubItem(block_contact,!userBlocked ? R.drawable.msg_block : R.drawable.msg_block,!userBlocked ? LocaleController.getString("BlockContact",R.string.BlockContact) : LocaleController.getString("Unblock",R.string.Unblock));
            }
          }
        }
      }
 else {
        item=menu.addItem(10,R.drawable.ic_ab_other);
        item.addSubItem(share_contact,R.drawable.msg_share,LocaleController.getString("ShareContact",R.string.ShareContact));
        item.addSubItem(block_contact,!userBlocked ? R.drawable.msg_block : R.drawable.msg_block,!userBlocked ? LocaleController.getString("BlockContact",R.string.BlockContact) : LocaleController.getString("Unblock",R.string.Unblock));
        item.addSubItem(edit_contact,R.drawable.msg_edit,LocaleController.getString("EditContact",R.string.EditContact));
        item.addSubItem(delete_contact,R.drawable.msg_delete,LocaleController.getString("DeleteContact",R.string.DeleteContact));
      }
    }
 else {
      item=menu.addItem(10,R.drawable.ic_ab_other);
      item.addSubItem(share_contact,R.drawable.msg_share,LocaleController.getString("ShareContact",R.string.ShareContact));
    }
  }
 else   if (chat_id != 0) {
    if (chat_id > 0) {
      TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(chat_id);
      if (ChatObject.isChannel(chat)) {
        if (ChatObject.hasAdminRights(chat) || chat.megagroup && ChatObject.canChangeChatInfo(chat)) {
          editItem=menu.addItem(edit_channel,R.drawable.group_edit_profile);
        }
        if (!chat.megagroup && chatInfo != null && chatInfo.can_view_stats) {
          if (item == null) {
            item=menu.addItem(10,R.drawable.ic_ab_other);
          }
          item.addSubItem(statistics,R.drawable.msg_stats,LocaleController.getString("Statistics",R.string.Statistics));
        }
        if (chat.megagroup) {
          if (item == null) {
            item=menu.addItem(10,R.drawable.ic_ab_other);
          }
          item.addSubItem(search_members,R.drawable.msg_search,LocaleController.getString("SearchMembers",R.string.SearchMembers));
          if (!chat.creator && !chat.left && !chat.kicked) {
            item.addSubItem(leave_group,R.drawable.msg_leave,LocaleController.getString("LeaveMegaMenu",R.string.LeaveMegaMenu));
          }
        }
      }
 else {
        if (ChatObject.canChangeChatInfo(chat)) {
          editItem=menu.addItem(edit_channel,R.drawable.group_edit_profile);
        }
        item=menu.addItem(10,R.drawable.ic_ab_other);
        item.addSubItem(search_members,R.drawable.msg_search,LocaleController.getString("SearchMembers",R.string.SearchMembers));
        item.addSubItem(leave_group,R.drawable.msg_leave,LocaleController.getString("DeleteAndExit",R.string.DeleteAndExit));
      }
    }
  }
  if (item == null) {
    item=menu.addItem(10,R.drawable.ic_ab_other);
  }
  item.addSubItem(add_shortcut,R.drawable.msg_home,LocaleController.getString("AddShortcut",R.string.AddShortcut));
  item.setContentDescription(LocaleController.getString("AccDescrMoreOptions",R.string.AccDescrMoreOptions));
  if (editItem != null) {
    editItem.setContentDescription(LocaleController.getString("Edit",R.string.Edit));
  }
  if (callItem != null) {
    callItem.setContentDescription(LocaleController.getString("Call",R.string.Call));
  }
}
