/** 
 * http://localhost:8080/zipkin
 * @param name name
 * @return map
 */
@RequestMapping("/helloZipkin") public Map<String,Object> zipkin(@RequestParam(value="name",defaultValue="SOFATracer Zipkin Remote Report") String name){
  Map<String,Object> resultMap=new HashMap<String,Object>();
  resultMap.put("success",true);
  resultMap.put("id",counter.incrementAndGet());
  resultMap.put("content",String.format(TEMPLATE,name));
  return resultMap;
}
