/** 
 * http://localhost:8080/springmvc
 * @param name name
 * @return map
 */
@RequestMapping("/springmvc") public Map<String,Object> springmvc(@RequestParam(value="name",defaultValue="SOFATracer SpringMVC DEMO") String name){
  Map<String,Object> resultMap=new HashMap<String,Object>();
  resultMap.put("success",true);
  resultMap.put("id",counter.incrementAndGet());
  resultMap.put("content",String.format(TEMPLATE,name));
  return resultMap;
}
