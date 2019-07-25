/** 
 * ????
 */
@GetMapping("/detail") public R<LogUsual> detail(LogUsual log){
  return R.data(logService.getOne(Condition.getQueryWrapper(log)));
}
