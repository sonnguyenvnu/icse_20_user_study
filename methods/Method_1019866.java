/** 
 * Request http://localhost:8080/rest?name=
 * @return Map of Result
 */
@RequestMapping("/rest") public Map<String,Object> rest(){
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("count",counter.incrementAndGet());
  return map;
}
