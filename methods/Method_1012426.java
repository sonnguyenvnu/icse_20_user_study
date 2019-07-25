/** 
 * ?? 
 */
@PostMapping("/remove") @ApiOperationSupport(order=6) @ApiOperation(value="????",notes="??ids") public R remove(@ApiParam(value="????",required=true) @RequestParam String ids){
  return R.status(clientService.deleteLogic(Func.toIntList(ids)));
}
