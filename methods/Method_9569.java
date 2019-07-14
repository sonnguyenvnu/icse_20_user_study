public static String formatRulesString(int currentAccount,int rulesType){
  ArrayList<TLRPC.PrivacyRule> privacyRules=ContactsController.getInstance(currentAccount).getPrivacyRules(rulesType);
  if (privacyRules.size() == 0) {
    if (rulesType == 3) {
      return LocaleController.getString("P2PNobody",R.string.P2PNobody);
    }
 else {
      return LocaleController.getString("LastSeenNobody",R.string.LastSeenNobody);
    }
  }
  int type=-1;
  int plus=0;
  int minus=0;
  for (int a=0; a < privacyRules.size(); a++) {
    TLRPC.PrivacyRule rule=privacyRules.get(a);
    if (rule instanceof TLRPC.TL_privacyValueAllowChatParticipants) {
      TLRPC.TL_privacyValueAllowChatParticipants participants=(TLRPC.TL_privacyValueAllowChatParticipants)rule;
      for (int b=0, N=participants.chats.size(); b < N; b++) {
        TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(participants.chats.get(b));
        if (chat == null) {
          continue;
        }
        plus+=chat.participants_count;
      }
    }
 else     if (rule instanceof TLRPC.TL_privacyValueDisallowChatParticipants) {
      TLRPC.TL_privacyValueDisallowChatParticipants participants=(TLRPC.TL_privacyValueDisallowChatParticipants)rule;
      for (int b=0, N=participants.chats.size(); b < N; b++) {
        TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(participants.chats.get(b));
        if (chat == null) {
          continue;
        }
        minus+=chat.participants_count;
      }
    }
 else     if (rule instanceof TLRPC.TL_privacyValueAllowUsers) {
      TLRPC.TL_privacyValueAllowUsers privacyValueAllowUsers=(TLRPC.TL_privacyValueAllowUsers)rule;
      plus+=privacyValueAllowUsers.users.size();
    }
 else     if (rule instanceof TLRPC.TL_privacyValueDisallowUsers) {
      TLRPC.TL_privacyValueDisallowUsers privacyValueDisallowUsers=(TLRPC.TL_privacyValueDisallowUsers)rule;
      minus+=privacyValueDisallowUsers.users.size();
    }
 else     if (type == -1) {
      if (rule instanceof TLRPC.TL_privacyValueAllowAll) {
        type=0;
      }
 else       if (rule instanceof TLRPC.TL_privacyValueDisallowAll) {
        type=1;
      }
 else {
        type=2;
      }
    }
  }
  if (type == 0 || type == -1 && minus > 0) {
    if (rulesType == 3) {
      if (minus == 0) {
        return LocaleController.getString("P2PEverybody",R.string.P2PEverybody);
      }
 else {
        return LocaleController.formatString("P2PEverybodyMinus",R.string.P2PEverybodyMinus,minus);
      }
    }
 else {
      if (minus == 0) {
        return LocaleController.getString("LastSeenEverybody",R.string.LastSeenEverybody);
      }
 else {
        return LocaleController.formatString("LastSeenEverybodyMinus",R.string.LastSeenEverybodyMinus,minus);
      }
    }
  }
 else   if (type == 2 || type == -1 && minus > 0 && plus > 0) {
    if (rulesType == 3) {
      if (plus == 0 && minus == 0) {
        return LocaleController.getString("P2PContacts",R.string.P2PContacts);
      }
 else {
        if (plus != 0 && minus != 0) {
          return LocaleController.formatString("P2PContactsMinusPlus",R.string.P2PContactsMinusPlus,minus,plus);
        }
 else         if (minus != 0) {
          return LocaleController.formatString("P2PContactsMinus",R.string.P2PContactsMinus,minus);
        }
 else {
          return LocaleController.formatString("P2PContactsPlus",R.string.P2PContactsPlus,plus);
        }
      }
    }
 else {
      if (plus == 0 && minus == 0) {
        return LocaleController.getString("LastSeenContacts",R.string.LastSeenContacts);
      }
 else {
        if (plus != 0 && minus != 0) {
          return LocaleController.formatString("LastSeenContactsMinusPlus",R.string.LastSeenContactsMinusPlus,minus,plus);
        }
 else         if (minus != 0) {
          return LocaleController.formatString("LastSeenContactsMinus",R.string.LastSeenContactsMinus,minus);
        }
 else {
          return LocaleController.formatString("LastSeenContactsPlus",R.string.LastSeenContactsPlus,plus);
        }
      }
    }
  }
 else   if (type == 1 || plus > 0) {
    if (rulesType == 3) {
      if (plus == 0) {
        return LocaleController.getString("P2PNobody",R.string.P2PNobody);
      }
 else {
        return LocaleController.formatString("P2PNobodyPlus",R.string.P2PNobodyPlus,plus);
      }
    }
 else {
      if (plus == 0) {
        return LocaleController.getString("LastSeenNobody",R.string.LastSeenNobody);
      }
 else {
        return LocaleController.formatString("LastSeenNobodyPlus",R.string.LastSeenNobodyPlus,plus);
      }
    }
  }
  return "unknown";
}
