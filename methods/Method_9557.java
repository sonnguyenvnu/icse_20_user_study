private void checkPrivacy(){
  currentPlus=new ArrayList<>();
  currentMinus=new ArrayList<>();
  ArrayList<TLRPC.PrivacyRule> privacyRules=ContactsController.getInstance(currentAccount).getPrivacyRules(rulesType);
  if (privacyRules == null || privacyRules.size() == 0) {
    currentType=1;
  }
 else {
    int type=-1;
    for (int a=0; a < privacyRules.size(); a++) {
      TLRPC.PrivacyRule rule=privacyRules.get(a);
      if (rule instanceof TLRPC.TL_privacyValueAllowChatParticipants) {
        TLRPC.TL_privacyValueAllowChatParticipants privacyValueAllowChatParticipants=(TLRPC.TL_privacyValueAllowChatParticipants)rule;
        for (int b=0, N=privacyValueAllowChatParticipants.chats.size(); b < N; b++) {
          currentPlus.add(-privacyValueAllowChatParticipants.chats.get(b));
        }
      }
 else       if (rule instanceof TLRPC.TL_privacyValueDisallowChatParticipants) {
        TLRPC.TL_privacyValueDisallowChatParticipants privacyValueDisallowChatParticipants=(TLRPC.TL_privacyValueDisallowChatParticipants)rule;
        for (int b=0, N=privacyValueDisallowChatParticipants.chats.size(); b < N; b++) {
          currentMinus.add(-privacyValueDisallowChatParticipants.chats.get(b));
        }
      }
 else       if (rule instanceof TLRPC.TL_privacyValueAllowUsers) {
        TLRPC.TL_privacyValueAllowUsers privacyValueAllowUsers=(TLRPC.TL_privacyValueAllowUsers)rule;
        currentPlus.addAll(privacyValueAllowUsers.users);
      }
 else       if (rule instanceof TLRPC.TL_privacyValueDisallowUsers) {
        TLRPC.TL_privacyValueDisallowUsers privacyValueDisallowUsers=(TLRPC.TL_privacyValueDisallowUsers)rule;
        currentMinus.addAll(privacyValueDisallowUsers.users);
      }
 else       if (type == -1) {
        if (rule instanceof TLRPC.TL_privacyValueAllowAll) {
          type=0;
        }
 else         if (rule instanceof TLRPC.TL_privacyValueDisallowAll) {
          type=1;
        }
 else {
          type=2;
        }
      }
    }
    if (type == 0 || type == -1 && currentMinus.size() > 0) {
      currentType=0;
    }
 else     if (type == 2 || type == -1 && currentMinus.size() > 0 && currentPlus.size() > 0) {
      currentType=2;
    }
 else     if (type == 1 || type == -1 && currentPlus.size() > 0) {
      currentType=1;
    }
    if (doneButton != null) {
      doneButton.setVisibility(View.GONE);
    }
  }
  initialPlus.clear();
  initialMinus.clear();
  initialRulesType=currentType;
  initialPlus.addAll(currentPlus);
  initialMinus.addAll(currentMinus);
  updateRows();
}
