/** 
 * Action for performing a checkout on a given shopping cart
 * @param id
 * @param order
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
@RequestMapping(value={"/auth/cart/{id}/checkout"},method=RequestMethod.POST) @ResponseStatus(HttpStatus.ACCEPTED) @ResponseBody @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="string",defaultValue="DEFAULT"),@ApiImplicitParam(name="lang",dataType="string",defaultValue="en")}) public PersistableOrderApi checkout(@PathVariable final Long id,@Valid @RequestBody PersistableOrderApi order,@ApiIgnore MerchantStore merchantStore,@ApiIgnore Language language,HttpServletRequest request,HttpServletResponse response,Locale locale) throws Exception {
  try {
    Principal principal=request.getUserPrincipal();
    String userName=principal.getName();
    Customer customer=customerService.getByNick(userName);
    if (customer == null) {
      response.sendError(401,"Error while performing checkout customer not authorized");
      return null;
    }
    order.setShoppingCartId(id);
    order.setCustomerId(customer.getId());
    Order modelOrder=orderFacade.processOrder(order,customer,merchantStore,language,locale);
    Long orderId=modelOrder.getId();
    order.setId(orderId);
    return order;
  }
 catch (  Exception e) {
    LOGGER.error("Error while processing checkout",e);
    try {
      response.sendError(503,"Error while processing checkout " + e.getMessage());
    }
 catch (    Exception ignore) {
    }
    return null;
  }
}
