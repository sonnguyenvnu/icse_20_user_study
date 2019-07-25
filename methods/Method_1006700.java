@ResponseStatus(HttpStatus.OK) @GetMapping(value={"/private/stores"},produces=MediaType.APPLICATION_JSON_VALUE) @ApiOperation(httpMethod="GET",value="Check list of stores",notes="",response=ReadableMerchantStoreList.class) public ReadableMerchantStoreList list(@RequestParam(value="start",required=false) Integer start,@RequestParam(value="length",required=false) Integer count,@RequestParam(value="code",required=false) String code,HttpServletRequest request){
  MerchantStoreCriteria criteria=createMerchantStoreCriteria(start,count,request);
  String drawParam=request.getParameter("draw");
  return storeFacade.getByCriteria(criteria,drawParam,languageService.defaultLanguage());
}
