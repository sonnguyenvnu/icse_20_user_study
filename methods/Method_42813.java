/** 
 * ????????????????null???????""
 * @return
 */
public Map<String,Object> getParamMap_NullStr(Map map){
  Map<String,Object> parameters=new HashMap<String,Object>();
  Set keys=map.keySet();
  for (  Object key : keys) {
    String value=this.getString(key.toString());
    if (value == null) {
      value="";
    }
    parameters.put(key.toString(),value);
  }
  return parameters;
}
