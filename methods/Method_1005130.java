@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE) @ResponseBody @ApiOperation(value="????") public ResponseEntity<?> create(@ApiParam(value="????") @RequestBody JformOrderMainPage jformOrderMainPage,UriComponentsBuilder uriBuilder){
  Set<ConstraintViolation<JformOrderMainPage>> failures=validator.validate(jformOrderMainPage);
  if (!failures.isEmpty()) {
    return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures),HttpStatus.BAD_REQUEST);
  }
  List<JformOrderCustomerEntity> jformOrderCustomerList=jformOrderMainPage.getJformOrderCustomerList();
  List<JformOrderTicketEntity> jformOrderTicketList=jformOrderMainPage.getJformOrderTicketList();
  JformOrderMainEntity jformOrderMain=new JformOrderMainEntity();
  try {
    MyBeanUtils.copyBeanNotNull2Bean(jformOrderMainPage,jformOrderMain);
  }
 catch (  Exception e) {
    logger.info(e.getMessage());
  }
  jformOrderMainService.addMain(jformOrderMain,jformOrderCustomerList,jformOrderTicketList);
  String id=jformOrderMainPage.getId();
  URI uri=uriBuilder.path("/rest/jformOrderMainController/" + id).build().toUri();
  HttpHeaders headers=new HttpHeaders();
  headers.setLocation(uri);
  return new ResponseEntity(headers,HttpStatus.CREATED);
}
