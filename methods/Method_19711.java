@ApiOperation(value="????",notes="????id????") @ApiImplicitParam(name="id",value="??id",required=true,dataType="Long",paramType="path") @DeleteMapping("/{id}") public @ResponseBody Map<String,Object> deleteUser(@PathVariable(value="id") Long id){
  Map<String,Object> map=new HashMap<>();
  map.put("result","success");
  return map;
}
