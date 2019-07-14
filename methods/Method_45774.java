/** 
 * ???????
 * @param serviceKey ?????
 * @return ???
 */
protected SofaRouteException unavailableProviderException(String serviceKey,String providerInfo){
  return new SofaRouteException(LogCodes.getLog(LogCodes.ERROR_TARGET_URL_INVALID,serviceKey,providerInfo));
}
