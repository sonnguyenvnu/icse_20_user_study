@ApiOperation(value="????",notes="????id????") @ApiImplicitParams({@ApiImplicitParam(name="id",value="??id",required=true,dataType="Long",paramType="path"),@ApiImplicitParam(name="user",value="????",required=true,dataType="User")}) @PutMapping("/{id}") public @ResponseBody Map<String,Object> updateUser(@PathVariable(value="id") Long id,@RequestBody User user){
  Map<String,Object> map=new HashMap<>();
  map.put("result","success");
  return map;
}
