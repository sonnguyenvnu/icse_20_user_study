/** 
 * Request http://localhost:8080/httpclient?name=
 * @param name name
 * @return Map of Result
 */
@RequestMapping("/httpclient") public Map<String,Object> greeting(@RequestParam(value="name",defaultValue="httpclient") String name){
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("count",counter.incrementAndGet());
  map.put("name",name);
  return map;
}
