@ResponseStatus(HttpStatus.OK) @PostMapping(value={"/private/user/unique"},produces=MediaType.APPLICATION_JSON_VALUE) @ApiOperation(httpMethod="POST",value="Check if username already exists",notes="",response=EntityExists.class) public ResponseEntity<EntityExists> exists(@ApiIgnore MerchantStore merchantStore,@ApiIgnore Language language,@RequestBody UniqueEntity userName){
  boolean isUserExist=true;
  try {
    userFacade.findByUserName(userName.getUnique(),merchantStore.getCode(),language);
  }
 catch (  ResourceNotFoundException e) {
    isUserExist=false;
  }
  return new ResponseEntity<EntityExists>(new EntityExists(isUserExist),HttpStatus.OK);
}
