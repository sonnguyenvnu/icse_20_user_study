/** 
 * ????????????????????????
 * @param configKey   ???
 * @param configValue ???
 * @throws SofaRpcRuntimeException ????
 */
protected static void checkNormalWithCommaColon(String configKey,String configValue) throws SofaRpcRuntimeException {
  checkPattern(configKey,configValue,NORMAL_COMMA_COLON,"only allow a-zA-Z0-9 '-' '_' '.' ':' ','");
}
