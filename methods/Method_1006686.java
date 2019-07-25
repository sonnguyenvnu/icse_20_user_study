@ResponseStatus(HttpStatus.OK) @GetMapping(value={"/private/category/unique"},produces=MediaType.APPLICATION_JSON_VALUE) @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="string",defaultValue="DEFAULT"),@ApiImplicitParam(name="lang",dataType="string",defaultValue="en")}) @ApiOperation(httpMethod="GET",value="Check if category code already exists",notes="",response=EntityExists.class) public ResponseEntity<EntityExists> exists(@RequestParam(value="code") String code,@ApiIgnore MerchantStore merchantStore,@ApiIgnore Language language){
  boolean isCategoryExist=categoryFacade.existByCode(merchantStore,code);
  return new ResponseEntity<EntityExists>(new EntityExists(isCategoryExist),HttpStatus.OK);
}
