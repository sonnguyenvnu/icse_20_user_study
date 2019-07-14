@Authorize(action=Permission.ACTION_GET) @GetMapping(path="/ids") @ApiOperation("??????????") default ResponseMessage<List<E>> getByPrimaryKey(@RequestParam List<PK> ids){
  return ok(assertNotNull(getService().selectByPk(ids)));
}
