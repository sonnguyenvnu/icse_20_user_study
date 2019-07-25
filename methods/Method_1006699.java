@GetMapping(value={"/store/{store}"},produces=MediaType.APPLICATION_JSON_VALUE) @ApiOperation(httpMethod="GET",value="Get merchant store",notes="",response=ReadableMerchantStore.class) public ReadableMerchantStore store(@PathVariable String store,@RequestParam(value="lang",required=false) String lang){
  return storeFacade.getByCode(store,lang);
}
