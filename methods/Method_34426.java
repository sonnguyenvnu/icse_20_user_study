private static String getApprovalKey(String clientId,String userName){
  return clientId + (userName == null ? "" : ":" + userName);
}
