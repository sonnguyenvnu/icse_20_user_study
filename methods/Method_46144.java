/** 
 * ???????
 * @param subscriberRegistration ????????
 * @param group           ??
 */
private void addAttributes(SubscriberRegistration subscriberRegistration,String group){
  if (StringUtils.isNotEmpty(group)) {
    subscriberRegistration.setGroup(group);
  }
  subscriberRegistration.setScopeEnum(ScopeEnum.global);
}
