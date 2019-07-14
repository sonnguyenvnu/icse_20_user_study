/** 
 * ???????????>0)
 * @param configKey   ???
 * @param configValue ???
 * @throws SofaRpcRuntimeException ????
 */
protected static void checkPositiveInteger(String configKey,int configValue) throws SofaRpcRuntimeException {
  if (configValue <= 0) {
    throw ExceptionUtils.buildRuntime(configKey,configValue + "","must > 0");
  }
}
