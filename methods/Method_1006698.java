@ResponseStatus(HttpStatus.OK) @RequestMapping(value="/customers/{id}/cart",method=RequestMethod.GET) @ApiOperation(httpMethod="GET",value="Get a chopping cart by id",notes="",produces="application/json",response=ReadableShoppingCart.class) @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="String",defaultValue="DEFAULT"),@ApiImplicitParam(name="lang",dataType="String",defaultValue="en")}) public @ResponseBody ReadableShoppingCart get(@PathVariable Long id,@ApiIgnore MerchantStore merchantStore,@ApiIgnore Language language,HttpServletResponse response){
  try {
    Customer customer=customerService.getById(id);
    if (customer == null) {
      response.sendError(404,"No Customer found for ID : " + id);
      return null;
    }
    ReadableShoppingCart cart=shoppingCartFacade.getCart(customer,merchantStore,language);
    if (cart == null) {
      response.sendError(404,"No ShoppingCart found for customer ID : " + id);
      return null;
    }
    return cart;
  }
 catch (  Exception e) {
    LOGGER.error("Error while getting cart",e);
    try {
      response.sendError(503,"Error while getting cart " + e.getMessage());
    }
 catch (    Exception ignore) {
    }
    return null;
  }
}
