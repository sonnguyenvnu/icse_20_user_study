private void applyCurrentPrivacySettings(){
  TLRPC.TL_account_setPrivacy req=new TLRPC.TL_account_setPrivacy();
  if (rulesType == PRIVACY_RULES_TYPE_PHONE) {
    req.key=new TLRPC.TL_inputPrivacyKeyPhoneNumber();
  }
 else   if (rulesType == PRIVACY_RULES_TYPE_FORWARDS) {
    req.key=new TLRPC.TL_inputPrivacyKeyForwards();
  }
 else   if (rulesType == PRIVACY_RULES_TYPE_PHOTO) {
    req.key=new TLRPC.TL_inputPrivacyKeyProfilePhoto();
  }
 else   if (rulesType == PRIVACY_RULES_TYPE_P2P) {
    req.key=new TLRPC.TL_inputPrivacyKeyPhoneP2P();
  }
 else   if (rulesType == PRIVACY_RULES_TYPE_CALLS) {
    req.key=new TLRPC.TL_inputPrivacyKeyPhoneCall();
  }
 else   if (rulesType == PRIVACY_RULES_TYPE_INVITE) {
    req.key=new TLRPC.TL_inputPrivacyKeyChatInvite();
  }
 else {
    req.key=new TLRPC.TL_inputPrivacyKeyStatusTimestamp();
  }
  if (currentType != 0 && currentPlus.size() > 0) {
    TLRPC.TL_inputPrivacyValueAllowUsers usersRule=new TLRPC.TL_inputPrivacyValueAllowUsers();
    TLRPC.TL_inputPrivacyValueAllowChatParticipants chatsRule=new TLRPC.TL_inputPrivacyValueAllowChatParticipants();
    for (int a=0; a < currentPlus.size(); a++) {
      int id=currentPlus.get(a);
      if (id > 0) {
        TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(id);
        if (user != null) {
          TLRPC.InputUser inputUser=MessagesController.getInstance(currentAccount).getInputUser(user);
          if (inputUser != null) {
            usersRule.users.add(inputUser);
          }
        }
      }
 else {
        chatsRule.chats.add(-id);
      }
    }
    req.rules.add(usersRule);
    req.rules.add(chatsRule);
  }
  if (currentType != 1 && currentMinus.size() > 0) {
    TLRPC.TL_inputPrivacyValueDisallowUsers usersRule=new TLRPC.TL_inputPrivacyValueDisallowUsers();
    TLRPC.TL_inputPrivacyValueDisallowChatParticipants chatsRule=new TLRPC.TL_inputPrivacyValueDisallowChatParticipants();
    for (int a=0; a < currentMinus.size(); a++) {
      int id=currentMinus.get(a);
      if (id > 0) {
        TLRPC.User user=getMessagesController().getUser(id);
        if (user != null) {
          TLRPC.InputUser inputUser=getMessagesController().getInputUser(user);
          if (inputUser != null) {
            usersRule.users.add(inputUser);
          }
        }
      }
 else {
        chatsRule.chats.add(-id);
      }
    }
    req.rules.add(usersRule);
    req.rules.add(chatsRule);
  }
  if (currentType == 0) {
    req.rules.add(new TLRPC.TL_inputPrivacyValueAllowAll());
  }
 else   if (currentType == 1) {
    req.rules.add(new TLRPC.TL_inputPrivacyValueDisallowAll());
  }
 else   if (currentType == 2) {
    req.rules.add(new TLRPC.TL_inputPrivacyValueAllowContacts());
  }
  AlertDialog progressDialog=null;
  if (getParentActivity() != null) {
    progressDialog=new AlertDialog(getParentActivity(),3);
    progressDialog.setCanCacnel(false);
    progressDialog.show();
  }
  final AlertDialog progressDialogFinal=progressDialog;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    try {
      if (progressDialogFinal != null) {
        progressDialogFinal.dismiss();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    if (error == null) {
      TLRPC.TL_account_privacyRules privacyRules=(TLRPC.TL_account_privacyRules)response;
      MessagesController.getInstance(currentAccount).putUsers(privacyRules.users,false);
      MessagesController.getInstance(currentAccount).putChats(privacyRules.chats,false);
      ContactsController.getInstance(currentAccount).setPrivacyRules(privacyRules.rules,rulesType);
      finishFragment();
    }
 else {
      showErrorAlert();
    }
  }
),ConnectionsManager.RequestFlagFailOnServerErrors);
}
