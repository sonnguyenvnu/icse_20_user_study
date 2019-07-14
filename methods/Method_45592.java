/** 
 * Add request props.
 * @param map the map
 */
public void addRequestProps(Map<String,Object> map){
  if (map == null || map.isEmpty()) {
    return;
  }
  if (requestProps == null) {
    requestProps=new HashMap<String,Object>(16);
  }
  requestProps.putAll(map);
}
