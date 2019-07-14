/** 
 * ???????????
 * @param sb ??
 */
private static void addCommonAttrs(StringBuilder sb){
  sb.append(getKeyPairs(ATTR_START_TIME,RpcRuntimeContext.now()));
}
