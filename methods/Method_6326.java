public void setPrivacyRules(ArrayList<TLRPC.PrivacyRule> rules,int type){
switch (type) {
case PRIVACY_RULES_TYPE_LASTSEEN:
    lastseenPrivacyRules=rules;
  break;
case PRIVACY_RULES_TYPE_INVITE:
groupPrivacyRules=rules;
break;
case PRIVACY_RULES_TYPE_CALLS:
callPrivacyRules=rules;
break;
case PRIVACY_RULES_TYPE_P2P:
p2pPrivacyRules=rules;
break;
case PRIVACY_RULES_TYPE_PHOTO:
profilePhotoPrivacyRules=rules;
break;
case PRIVACY_RULES_TYPE_FORWARDS:
forwardsPrivacyRules=rules;
break;
case PRIVACY_RULES_TYPE_PHONE:
phonePrivacyRules=rules;
break;
}
NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.privacyRulesUpdated);
reloadContactsStatuses();
}
