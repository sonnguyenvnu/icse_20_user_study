@PostMapping(value="/login",consumes=MediaType.APPLICATION_JSON_VALUE) @ApiOperation("???????,json??") public ResponseMessage<Map<String,Object>> authorize(@ApiParam(example="{\"username\":\"admin\",\"password\":\"admin\"}") @RequestBody Map<String,String> parameter){
  return doLogin(parameter.get("username"),parameter.get("password"),parameter);
}
