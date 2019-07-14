@Authorize(action=Permission.ACTION_DELETE) @DeleteMapping(path="/{id:.+}") @ApiOperation("????") default ResponseMessage<E> deleteByPrimaryKey(@PathVariable PK id){
  return ok(getService().deleteByPk(id));
}
