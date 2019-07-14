public void loadPrivacySettings(){
  if (loadingDeleteInfo == 0) {
    loadingDeleteInfo=1;
    TLRPC.TL_account_getAccountTTL req=new TLRPC.TL_account_getAccountTTL();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      if (error == null) {
        TLRPC.TL_accountDaysTTL ttl=(TLRPC.TL_accountDaysTTL)response;
        deleteAccountTTL=ttl.days;
        loadingDeleteInfo=2;
      }
 else {
        loadingDeleteInfo=0;
      }
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.privacyRulesUpdated);
    }
));
  }
  for (int a=0; a < loadingPrivacyInfo.length; a++) {
    if (loadingPrivacyInfo[a] != 0) {
      continue;
    }
    loadingPrivacyInfo[a]=1;
    final int num=a;
    TLRPC.TL_account_getPrivacy req=new TLRPC.TL_account_getPrivacy();
switch (num) {
case PRIVACY_RULES_TYPE_LASTSEEN:
      req.key=new TLRPC.TL_inputPrivacyKeyStatusTimestamp();
    break;
case PRIVACY_RULES_TYPE_INVITE:
  req.key=new TLRPC.TL_inputPrivacyKeyChatInvite();
break;
case PRIVACY_RULES_TYPE_CALLS:
req.key=new TLRPC.TL_inputPrivacyKeyPhoneCall();
break;
case PRIVACY_RULES_TYPE_P2P:
req.key=new TLRPC.TL_inputPrivacyKeyPhoneP2P();
break;
case PRIVACY_RULES_TYPE_PHOTO:
req.key=new TLRPC.TL_inputPrivacyKeyProfilePhoto();
break;
case PRIVACY_RULES_TYPE_FORWARDS:
req.key=new TLRPC.TL_inputPrivacyKeyForwards();
break;
case PRIVACY_RULES_TYPE_PHONE:
default :
req.key=new TLRPC.TL_inputPrivacyKeyPhoneNumber();
break;
}
ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
if (error == null) {
TLRPC.TL_account_privacyRules rules=(TLRPC.TL_account_privacyRules)response;
MessagesController.getInstance(currentAccount).putUsers(rules.users,false);
MessagesController.getInstance(currentAccount).putChats(rules.chats,false);
switch (num) {
case PRIVACY_RULES_TYPE_LASTSEEN:
lastseenPrivacyRules=rules.rules;
break;
case PRIVACY_RULES_TYPE_INVITE:
groupPrivacyRules=rules.rules;
break;
case PRIVACY_RULES_TYPE_CALLS:
callPrivacyRules=rules.rules;
break;
case PRIVACY_RULES_TYPE_P2P:
p2pPrivacyRules=rules.rules;
break;
case PRIVACY_RULES_TYPE_PHOTO:
profilePhotoPrivacyRules=rules.rules;
break;
case PRIVACY_RULES_TYPE_FORWARDS:
forwardsPrivacyRules=rules.rules;
break;
case PRIVACY_RULES_TYPE_PHONE:
default :
phonePrivacyRules=rules.rules;
break;
}
loadingPrivacyInfo[num]=2;
}
 else {
loadingPrivacyInfo[num]=0;
}
NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.privacyRulesUpdated);
}
));
}
NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.privacyRulesUpdated);
}
