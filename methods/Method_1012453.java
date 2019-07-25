/** 
 * ??
 */
@PostMapping("/remove") @ApiOperationSupport(order=5) @ApiOperation(value="??",notes="?????") public R remove(@RequestParam String ids){
  return R.status(userService.deleteLogic(Func.toIntList(ids)));
}
