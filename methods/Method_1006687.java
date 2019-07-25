@PutMapping(value="/private/category/{id}",produces={APPLICATION_JSON_VALUE,APPLICATION_XML_VALUE}) @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="string",defaultValue="DEFAULT")}) public PersistableCategory update(@PathVariable Long id,@Valid @RequestBody PersistableCategory category,@ApiIgnore MerchantStore merchantStore){
  return categoryFacade.saveCategory(merchantStore,category);
}
