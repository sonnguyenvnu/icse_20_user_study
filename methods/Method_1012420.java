/** 
 * ????
 */
@GetMapping("/detail") public R<LogError> detail(LogError logError){
  return R.data(errorLogService.getOne(Condition.getQueryWrapper(logError)));
}
