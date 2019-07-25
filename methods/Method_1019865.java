/** 
 * Request http://localhost:8081/okhttp?name=
 * @param name name
 * @return Map of Result
 */
@RequestMapping("/okhttp") public Map<String,Object> greeting(@RequestParam(value="name",defaultValue="okhttp") String name){
  Map<String,Object> map=new HashMap<>();
  map.put("count",counter.incrementAndGet());
  map.put("name",name);
  return map;
}
