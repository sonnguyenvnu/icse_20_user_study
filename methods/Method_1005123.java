@RequestMapping(value="/{id}",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE) @ResponseBody @ApiOperation(value="???????",notes="???????") public ResponseMessage<?> update(@RequestBody JformOrderMain2Page jformOrderMain2Page){
  Set<ConstraintViolation<JformOrderMain2Page>> failures=validator.validate(jformOrderMain2Page);
  if (!failures.isEmpty()) {
    return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
  }
  List<JformOrderTicket2Entity> jformOrderTicket2List=jformOrderMain2Page.getJformOrderTicket2List();
  List<JformOrderCustomer2Entity> jformOrderCustomer2List=jformOrderMain2Page.getJformOrderCustomer2List();
  JformOrderMain2Entity jformOrderMain2=new JformOrderMain2Entity();
  try {
    MyBeanUtils.copyBeanNotNull2Bean(jformOrderMain2Page,jformOrderMain2);
  }
 catch (  Exception e) {
    logger.info(e.getMessage());
    return Result.error("?????????");
  }
  jformOrderMain2Service.updateMain(jformOrderMain2,jformOrderTicket2List,jformOrderCustomer2List);
  return Result.success();
}
