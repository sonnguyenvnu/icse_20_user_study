/** 
 * Returns byte array of created class.
 */
public byte[] create(){
  process();
  byte[] result=toByteArray();
  dumpClassInDebugFolder(result);
  if ((!proxetta.isForced()) && (!isProxyApplied())) {
    if (log.isDebugEnabled()) {
      log.debug("Proxy not applied: " + StringUtil.toSafeString(targetClassName));
    }
    return null;
  }
  if (log.isDebugEnabled()) {
    log.debug("Proxy created " + StringUtil.toSafeString(targetClassName));
  }
  return result;
}
