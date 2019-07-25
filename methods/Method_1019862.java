/** 
 * @param name name
 * @return map
 */
@RequestMapping("/datasource") public Map<String,Object> datasource(@RequestParam(value="name",defaultValue="SOFATracer DataSource DEMO") String name){
  Map<String,Object> resultMap=new HashMap<String,Object>();
  resultMap.put("success",true);
  resultMap.put("id",counter.incrementAndGet());
  resultMap.put("content",String.format(TEMPLATE,name));
  return resultMap;
}
