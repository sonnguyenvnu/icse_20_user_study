@GetMapping(value="/{code}/content/images",produces=MediaType.APPLICATION_JSON_VALUE) @ApiOperation(httpMethod="GET",value="Get store content images",notes="",response=ContentFolder.class) public ContentFolder images(@PathVariable String code,@RequestParam(value="path",required=false) String path,HttpServletRequest request,HttpServletResponse response) throws Exception {
  MerchantStore merchantStore=storeFacade.get(code);
  Language language=languageUtils.getRESTLanguage(request,merchantStore);
  String decodedPath=decodeContentPath(path);
  ContentFolder folder=contentFacade.getContentFolder(decodedPath,merchantStore);
  return folder;
}
