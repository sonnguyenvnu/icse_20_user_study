/** 
 * client logout
 * @param sessionId
 */
public static void logout(String sessionId){
  String storeKey=SsoSessionIdHelper.parseStoreKey(sessionId);
  if (storeKey == null) {
    return;
  }
  SsoLoginStore.remove(storeKey);
}
