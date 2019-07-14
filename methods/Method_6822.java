public void putChat(final TLRPC.Chat chat,boolean fromCache){
  if (chat == null) {
    return;
  }
  TLRPC.Chat oldChat=chats.get(chat.id);
  if (oldChat == chat) {
    return;
  }
  if (oldChat != null && !TextUtils.isEmpty(oldChat.username)) {
    objectsByUsernames.remove(oldChat.username.toLowerCase());
  }
  if (!TextUtils.isEmpty(chat.username)) {
    objectsByUsernames.put(chat.username.toLowerCase(),chat);
  }
  if (chat.min) {
    if (oldChat != null) {
      if (!fromCache) {
        oldChat.title=chat.title;
        oldChat.photo=chat.photo;
        oldChat.broadcast=chat.broadcast;
        oldChat.verified=chat.verified;
        oldChat.megagroup=chat.megagroup;
        if (chat.default_banned_rights != null) {
          oldChat.default_banned_rights=chat.default_banned_rights;
          oldChat.flags|=262144;
        }
        if (chat.admin_rights != null) {
          oldChat.admin_rights=chat.admin_rights;
          oldChat.flags|=16384;
        }
        if (chat.banned_rights != null) {
          oldChat.banned_rights=chat.banned_rights;
          oldChat.flags|=32768;
        }
        if (chat.username != null) {
          oldChat.username=chat.username;
          oldChat.flags|=64;
        }
 else {
          oldChat.flags=oldChat.flags & ~64;
          oldChat.username=null;
        }
        if (chat.participants_count != 0) {
          oldChat.participants_count=chat.participants_count;
        }
      }
    }
 else {
      chats.put(chat.id,chat);
    }
  }
 else {
    if (!fromCache) {
      if (oldChat != null) {
        if (chat.version != oldChat.version) {
          loadedFullChats.remove((Integer)chat.id);
        }
        if (oldChat.participants_count != 0 && chat.participants_count == 0) {
          chat.participants_count=oldChat.participants_count;
          chat.flags|=131072;
        }
        int oldFlags=oldChat.banned_rights != null ? oldChat.banned_rights.flags : 0;
        int newFlags=chat.banned_rights != null ? chat.banned_rights.flags : 0;
        int oldFlags2=oldChat.default_banned_rights != null ? oldChat.default_banned_rights.flags : 0;
        int newFlags2=chat.default_banned_rights != null ? chat.default_banned_rights.flags : 0;
        oldChat.default_banned_rights=chat.default_banned_rights;
        if (oldChat.default_banned_rights == null) {
          oldChat.flags&=~262144;
        }
 else {
          oldChat.flags|=262144;
        }
        oldChat.banned_rights=chat.banned_rights;
        if (oldChat.banned_rights == null) {
          oldChat.flags&=~32768;
        }
 else {
          oldChat.flags|=32768;
        }
        oldChat.admin_rights=chat.admin_rights;
        if (oldChat.admin_rights == null) {
          oldChat.flags&=~16384;
        }
 else {
          oldChat.flags|=16384;
        }
        if (oldFlags != newFlags || oldFlags2 != newFlags2) {
          AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.channelRightsUpdated,chat));
        }
      }
      chats.put(chat.id,chat);
    }
 else     if (oldChat == null) {
      chats.put(chat.id,chat);
    }
 else     if (oldChat.min) {
      chat.min=false;
      chat.title=oldChat.title;
      chat.photo=oldChat.photo;
      chat.broadcast=oldChat.broadcast;
      chat.verified=oldChat.verified;
      chat.megagroup=oldChat.megagroup;
      if (oldChat.default_banned_rights != null) {
        chat.default_banned_rights=oldChat.default_banned_rights;
        chat.flags|=262144;
      }
      if (oldChat.admin_rights != null) {
        chat.admin_rights=oldChat.admin_rights;
        chat.flags|=16384;
      }
      if (oldChat.banned_rights != null) {
        chat.banned_rights=oldChat.banned_rights;
        chat.flags|=32768;
      }
      if (oldChat.username != null) {
        chat.username=oldChat.username;
        chat.flags|=64;
      }
 else {
        chat.flags=chat.flags & ~64;
        chat.username=null;
      }
      if (oldChat.participants_count != 0 && chat.participants_count == 0) {
        chat.participants_count=oldChat.participants_count;
        chat.flags|=131072;
      }
      chats.put(chat.id,chat);
    }
  }
}
