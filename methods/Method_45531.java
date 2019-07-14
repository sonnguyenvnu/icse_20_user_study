/** 
 * ???????????????????
 * @param configKey   ???
 * @param configValue ???
 * @throws SofaRpcRuntimeException ????
 */
protected static void checkNormal(String configKey,String configValue) throws SofaRpcRuntimeException {
  checkPattern(configKey,configValue,NORMAL,"only allow a-zA-Z0-9 '-' '_' '.'");
}
