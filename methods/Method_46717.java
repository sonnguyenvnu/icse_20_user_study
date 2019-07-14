/** 
 * String Type App map.
 * @return appMap
 */
public String appMapString(){
  if (hasGroup()) {
    String appMap=Optional.ofNullable(this.fields.get(TracingConstants.APP_MAP)).orElse("");
    log.debug("App map: {}",appMap);
    return appMap;
  }
  raiseNonGroupException();
  return "{}";
}
