/** 
 * Normalize the client id.
 * @return the normalized client id.
 */
public static String _XXXXX_(String clientId){
  String normalizedClientId;
  if (clientId.equals(DistributedLogConstants.UNKNOWN_CLIENT_ID)) {
    normalizedClientId=getHostIpLockClientId();
  }
 else {
    normalizedClientId=clientId;
  }
  return normalizedClientId;
}