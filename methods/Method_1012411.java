/** 
 * ?????JSON????
 * @param status       ???
 * @param errorMessage ????
 * @return
 */
public static Map<String,Object> response(int status,String errorMessage){
  Map<String,Object> map=new HashMap<>(16);
  map.put("code",status);
  map.put("message",errorMessage);
  map.put("data",null);
  return map;
}
