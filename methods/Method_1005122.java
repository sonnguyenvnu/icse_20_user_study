@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE) @ResponseBody @ApiOperation(value="???????") public ResponseMessage<?> create(@ApiParam(name="???????") @RequestBody JformOrderMain2Page jformOrderMain2Page,UriComponentsBuilder uriBuilder){
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
  jformOrderMain2Service.addMain(jformOrderMain2,jformOrderTicket2List,jformOrderCustomer2List);
  return Result.success(jformOrderMain2);
}
