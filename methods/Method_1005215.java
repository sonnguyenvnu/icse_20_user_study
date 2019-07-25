@ApiOperation(value="?????") @RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE) @ResponseBody public ResponseMessage<?> create(@RequestBody TsBlackListEntity tsBlackList,HttpServletRequest request){
  InterfaceRuleDto interfaceRuleDto=InterfaceUtil.getInterfaceRuleDto(request,InterfaceEnum.blacklist_add);
  if (interfaceRuleDto == null) {
    return Result.error("??????????");
  }
  logger.info("create[{}]",GsonUtil.toJson(tsBlackList));
  Set<ConstraintViolation<TsBlackListEntity>> failures=validator.validate(tsBlackList);
  if (!failures.isEmpty()) {
    return Result.errorValid(BeanValidators.extractPropertyAndMessage(failures));
  }
  try {
    tsBlackListService.save(tsBlackList);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return Result.error("???????");
  }
  return Result.success(tsBlackList);
}
