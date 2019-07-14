/** 
 * ????Map??????????????
 * @param access
 * @return
 */
public static HashMap<RequestMethod,RequestRole[]> getAccessMap(MethodAccess access){
  if (access == null) {
    return null;
  }
  HashMap<RequestMethod,RequestRole[]> map=new HashMap<>();
  map.put(GET,access.GET());
  map.put(HEAD,access.HEAD());
  map.put(GETS,access.GETS());
  map.put(HEADS,access.HEADS());
  map.put(POST,access.POST());
  map.put(PUT,access.PUT());
  map.put(DELETE,access.DELETE());
  return map;
}
