@RequestMapping("/checkUsername") @ResponseBody public ResultGeekQ<Boolean> checkUsername(String username){
  ResultGeekQ<Boolean> result=ResultGeekQ.build();
  try {
    result.setData(this.logininfoService.checkUsername(username,Constants.USERTYPE_NORMAL));
  }
 catch (  RuntimeException e) {
    logger.error("??????????!",e);
    result.withError(ResultStatus.SYSTEM_ERROR);
  }
  return result;
}
