@RequestMapping(value="/{id}",method=RequestMethod.DELETE) @ResponseStatus(HttpStatus.NO_CONTENT) @ApiOperation(value="???????") public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true) @PathVariable("id") String id){
  logger.info("delete[{}]",id);
  if (StringUtils.isEmpty(id)) {
    return Result.error("ID????");
  }
  try {
    JformOrderMain2Entity jformOrderMain2=jformOrderMain2Service.get(JformOrderMain2Entity.class,id);
    jformOrderMain2Service.delMain(jformOrderMain2);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return Result.error("?????????");
  }
  return Result.success();
}
