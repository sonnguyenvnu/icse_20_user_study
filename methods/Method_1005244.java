@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE) @ResponseBody @ApiOperation(value="??????") public ResponseEntity<?> create(@ApiParam(name="????") @RequestBody TSUser user,UriComponentsBuilder uriBuilder){
  Set<ConstraintViolation<TSUser>> failures=validator.validate(user);
  if (!failures.isEmpty()) {
    return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures),HttpStatus.BAD_REQUEST);
  }
  userService.save(user);
  String id=user.getId();
  URI uri=uriBuilder.path("/rest/user/" + id).build().toUri();
  HttpHeaders headers=new HttpHeaders();
  headers.setLocation(uri);
  return new ResponseEntity(headers,HttpStatus.CREATED);
}
