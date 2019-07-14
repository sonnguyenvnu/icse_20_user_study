/** 
 * ????
 * @param callback ????
 * @return
 */
@GetMapping("/policy") @ResponseBody public Object policy(@RequestParam(required=false) String callback){
  JSONObject result=aliyunOssService.policy();
  if (StringUtils.isBlank(callback)) {
    return result;
  }
  MappingJacksonValue jsonp=new MappingJacksonValue(result);
  jsonp.setJsonpFunction(callback);
  return jsonp;
}
