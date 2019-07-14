public static void addUsersAndChatsFromMessage(TLRPC.Message message,ArrayList<Integer> usersToLoad,ArrayList<Integer> chatsToLoad){
  if (message.from_id != 0) {
    if (message.from_id > 0) {
      if (!usersToLoad.contains(message.from_id)) {
        usersToLoad.add(message.from_id);
      }
    }
 else {
      if (!chatsToLoad.contains(-message.from_id)) {
        chatsToLoad.add(-message.from_id);
      }
    }
  }
  if (message.via_bot_id != 0 && !usersToLoad.contains(message.via_bot_id)) {
    usersToLoad.add(message.via_bot_id);
  }
  if (message.action != null) {
    if (message.action.user_id != 0 && !usersToLoad.contains(message.action.user_id)) {
      usersToLoad.add(message.action.user_id);
    }
    if (message.action.channel_id != 0 && !chatsToLoad.contains(message.action.channel_id)) {
      chatsToLoad.add(message.action.channel_id);
    }
    if (message.action.chat_id != 0 && !chatsToLoad.contains(message.action.chat_id)) {
      chatsToLoad.add(message.action.chat_id);
    }
    if (!message.action.users.isEmpty()) {
      for (int a=0; a < message.action.users.size(); a++) {
        Integer uid=message.action.users.get(a);
        if (!usersToLoad.contains(uid)) {
          usersToLoad.add(uid);
        }
      }
    }
  }
  if (!message.entities.isEmpty()) {
    for (int a=0; a < message.entities.size(); a++) {
      TLRPC.MessageEntity entity=message.entities.get(a);
      if (entity instanceof TLRPC.TL_messageEntityMentionName) {
        usersToLoad.add(((TLRPC.TL_messageEntityMentionName)entity).user_id);
      }
 else       if (entity instanceof TLRPC.TL_inputMessageEntityMentionName) {
        usersToLoad.add(((TLRPC.TL_inputMessageEntityMentionName)entity).user_id.user_id);
      }
    }
  }
  if (message.media != null) {
    if (message.media.user_id != 0 && !usersToLoad.contains(message.media.user_id)) {
      usersToLoad.add(message.media.user_id);
    }
  }
  if (message.fwd_from != null) {
    if (message.fwd_from.from_id != 0) {
      if (!usersToLoad.contains(message.fwd_from.from_id)) {
        usersToLoad.add(message.fwd_from.from_id);
      }
    }
    if (message.fwd_from.channel_id != 0) {
      if (!chatsToLoad.contains(message.fwd_from.channel_id)) {
        chatsToLoad.add(message.fwd_from.channel_id);
      }
    }
    if (message.fwd_from.saved_from_peer != null) {
      if (message.fwd_from.saved_from_peer.user_id != 0) {
        if (!chatsToLoad.contains(message.fwd_from.saved_from_peer.user_id)) {
          usersToLoad.add(message.fwd_from.saved_from_peer.user_id);
        }
      }
 else       if (message.fwd_from.saved_from_peer.channel_id != 0) {
        if (!chatsToLoad.contains(message.fwd_from.saved_from_peer.channel_id)) {
          chatsToLoad.add(message.fwd_from.saved_from_peer.channel_id);
        }
      }
 else       if (message.fwd_from.saved_from_peer.chat_id != 0) {
        if (!chatsToLoad.contains(message.fwd_from.saved_from_peer.chat_id)) {
          chatsToLoad.add(message.fwd_from.saved_from_peer.chat_id);
        }
      }
    }
  }
  if (message.ttl < 0) {
    if (!chatsToLoad.contains(-message.ttl)) {
      chatsToLoad.add(-message.ttl);
    }
  }
}
