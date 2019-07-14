/** 
 * ???????
 * @param configuratorRegistration ????????
 * @param group           ??
 */
private void addAttributes(ConfiguratorRegistration configuratorRegistration,String group){
  if (StringUtils.isNotEmpty(group)) {
    configuratorRegistration.setGroup(group);
  }
}
