@ResponseStatus(HttpStatus.OK) @RequestMapping(value={"/private/products/{id}","/auth/products/{id}"},method=RequestMethod.PUT) @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="String",defaultValue="DEFAULT"),@ApiImplicitParam(name="lang",dataType="String",defaultValue="en")}) public @ResponseBody PersistableProduct update(@PathVariable Long id,@Valid @RequestBody PersistableProduct product,@ApiIgnore MerchantStore merchantStore,HttpServletRequest request,HttpServletResponse response){
  try {
    productFacade.saveProduct(merchantStore,product,merchantStore.getDefaultLanguage());
    return product;
  }
 catch (  Exception e) {
    LOGGER.error("Error while updating product",e);
    try {
      response.sendError(503,"Error while updating product " + e.getMessage());
    }
 catch (    Exception ignore) {
    }
    return null;
  }
}
