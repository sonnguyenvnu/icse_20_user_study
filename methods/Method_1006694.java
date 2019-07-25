@RequestMapping(value="/manufacturers/{id}",method=RequestMethod.GET) @ResponseStatus(HttpStatus.OK) @ResponseBody @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="String",defaultValue="DEFAULT"),@ApiImplicitParam(name="lang",dataType="String",defaultValue="en")}) public ReadableManufacturer get(@PathVariable Long id,@ApiIgnore MerchantStore merchantStore,@ApiIgnore Language language,HttpServletResponse response){
  try {
    ReadableManufacturer manufacturer=productFacade.getManufacturer(id,merchantStore,language);
    if (manufacturer == null) {
      response.sendError(404,"No Manufacturer found for ID : " + id);
    }
    return manufacturer;
  }
 catch (  Exception e) {
    LOGGER.error("Error while getting manufacturer",e);
    try {
      response.sendError(503,"Error while getting manufacturer " + e.getMessage());
    }
 catch (    Exception ignore) {
    }
  }
  return null;
}
