/** 
 * ??App
 * @param serviceId serviceId
 * @param address   address
 */
public void addApp(String serviceId,String address){
  if (hasGroup()) {
    JSONObject map=JSON.parseObject(this.fields.get(TracingConstants.APP_MAP));
    if (map.containsKey(serviceId)) {
      return;
    }
    map.put(serviceId,address);
    this.fields.put(TracingConstants.APP_MAP,JSON.toJSONString(map));
    return;
  }
  raiseNonGroupException();
}
