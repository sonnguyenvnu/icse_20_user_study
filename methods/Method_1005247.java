@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE) @ResponseBody public ResponseMessage<?> create(@ApiParam(name="??????") @RequestBody SuperQueryMainPage superQueryMainPage,UriComponentsBuilder uriBuilder){
  Set<ConstraintViolation<SuperQueryMainPage>> failures=validator.validate(superQueryMainPage);
  if (!failures.isEmpty()) {
    return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
  }
  List<SuperQueryTableEntity> superQueryTableList=superQueryMainPage.getSuperQueryTableList();
  List<SuperQueryFieldEntity> superQueryFieldList=superQueryMainPage.getSuperQueryFieldList();
  SuperQueryMainEntity superQueryMain=new SuperQueryMainEntity();
  try {
    MyBeanUtils.copyBeanNotNull2Bean(superQueryMainPage,superQueryMain);
  }
 catch (  Exception e) {
    logger.info(e.getMessage());
    return Result.error("????????");
  }
  superQueryMainService.addMain(superQueryMain,superQueryTableList,superQueryFieldList);
  return Result.success(superQueryMain);
}
