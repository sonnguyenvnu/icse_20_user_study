/** 
 * ????
 * @return
 */
@GetMapping("/dictionary") @ApiOperationSupport(order=6) @ApiOperation(value="????",notes="????") public R<List<Dict>> dictionary(String code){
  List<Dict> tree=dictService.getList(code);
  return R.data(tree);
}
