/** 
 * ???????????
 * @param sb ??
 */
private static void addCommonAttrs(StringBuilder sb){
  sb.append(getKeyPairs(RpcConstants.CONFIG_KEY_PID,RpcRuntimeContext.PID));
  sb.append(getKeyPairs(RpcConstants.CONFIG_KEY_LANGUAGE,JAVA));
  sb.append(getKeyPairs(RpcConstants.CONFIG_KEY_RPC_VERSION,Version.RPC_VERSION + ""));
}
