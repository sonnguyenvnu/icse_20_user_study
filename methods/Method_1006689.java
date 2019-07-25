/** 
 * Need type, name and entity
 * @param file
 */
@PostMapping(value="/private/content") @ResponseStatus(HttpStatus.CREATED) @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="String",defaultValue="DEFAULT"),@ApiImplicitParam(name="lang",dataType="String",defaultValue="en")}) public HttpEntity<String> upload(@RequestBody @Valid ContentFile file,@ApiIgnore MerchantStore merchantStore,@ApiIgnore Language language){
  String fileName=file.getName();
  contentFacade.addContentFile(file,merchantStore.getCode());
  String fileUrl=contentFacade.absolutePath(merchantStore,fileName);
  return new HttpEntity<String>(fileUrl);
}
