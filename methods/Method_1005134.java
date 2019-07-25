@RequestMapping(value="/{id}",method=RequestMethod.DELETE) @ResponseStatus(HttpStatus.NO_CONTENT) public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true) @PathVariable("id") String id){
  logger.info("delete[{}]",id);
  if (StringUtils.isEmpty(id)) {
    return Result.error("ID????");
  }
  try {
    JfromOrderEntity jfromOrder=jfromOrderService.get(JfromOrderEntity.class,id);
    jfromOrderService.delMain(jfromOrder);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return Result.error("????????");
  }
  return Result.success();
}
