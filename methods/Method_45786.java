/** 
 * ?????????????
 * @param serviceKey ?????
 * @return ???
 */
protected SofaRouteException noAvailableProviderException(String serviceKey){
  return new SofaRouteException(LogCodes.getLog(LogCodes.ERROR_NO_AVAILBLE_PROVIDER,serviceKey));
}
