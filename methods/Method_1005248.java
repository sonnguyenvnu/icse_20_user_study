@RequestMapping(value="/{id}",method=RequestMethod.DELETE) @ResponseStatus(HttpStatus.NO_CONTENT) public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true) @PathVariable("id") String id){
  logger.info("delete[{}]",id);
  if (StringUtils.isEmpty(id)) {
    return Result.error("ID????");
  }
  try {
    SuperQueryMainEntity superQueryMain=superQueryMainService.get(SuperQueryMainEntity.class,id);
    superQueryMainService.delMain(superQueryMain);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return Result.error("????????");
  }
  return Result.success();
}
