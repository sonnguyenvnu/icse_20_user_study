/** 
 * ???????????????????????????????
 * @param configKey   ???
 * @param configValue ???
 * @param pattern     ?????
 * @param message     ??
 * @throws SofaRpcRuntimeException
 */
protected static void checkPattern(String configKey,String configValue,Pattern pattern,String message) throws SofaRpcRuntimeException {
  if (configValue != null && !match(pattern,configValue)) {
    throw ExceptionUtils.buildRuntime(configKey,configValue,message);
  }
}
