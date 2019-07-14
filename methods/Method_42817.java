/** 
 * ?json??????map??  
 * @param jsonObjStr e.g. {'name':'get','int':1,'double',1.1,'null':null}  
 * @return Map  
 */
public HashMap<String,String> convertToMap(JSONParam[] params){
  HashMap<String,String> map=new HashMap<String,String>();
  for (  JSONParam param : params) {
    map.put(param.getName(),param.getValue());
  }
  return map;
}
