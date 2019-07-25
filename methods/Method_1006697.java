@PostMapping("/search/autocomplete") @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="String",defaultValue="DEFAULT"),@ApiImplicitParam(name="lang",dataType="String",defaultValue="en")}) public @ResponseBody ValueList autocomplete(@RequestBody SearchProductRequest searchRequest,@ApiIgnore MerchantStore merchantStore,@ApiIgnore Language language,HttpServletRequest request){
  return searchFacade.autocompleteRequest(searchRequest.getQuery(),merchantStore,language);
}
