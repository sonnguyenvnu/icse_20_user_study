/** 
 * ????
 */
@GetMapping("/detail") public R<LogApi> detail(LogApi log){
  return R.data(logService.getOne(Condition.getQueryWrapper(log)));
}
