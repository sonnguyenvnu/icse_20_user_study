@Authorize(action=Permission.ACTION_GET) @GetMapping(path="/{id:.+}") @ApiOperation("??????") default ResponseMessage<E> getByPrimaryKey(@PathVariable PK id){
  return ok(assertNotNull(getService().selectByPk(id)));
}
