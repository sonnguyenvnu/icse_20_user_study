/** 
 * This service calculates order total for a given shopping cart This method takes in consideration any applicable sales tax An optional request parameter accepts a quote id that was received using shipping api
 * @param quote
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
@RequestMapping(value={"/auth/cart/{code}/payment"},method=RequestMethod.GET) @ResponseBody @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="String",defaultValue="DEFAULT"),@ApiImplicitParam(name="lang",dataType="String",defaultValue="en")}) public ReadableOrderTotalSummary payment(@PathVariable final String code,@RequestParam(value="quote",required=false) Long quote,@ApiIgnore MerchantStore merchantStore,@ApiIgnore Language language,HttpServletRequest request,HttpServletResponse response){
  try {
    Principal principal=request.getUserPrincipal();
    String userName=principal.getName();
    Customer customer=customerService.getByNick(userName);
    if (customer == null) {
      response.sendError(503,"Error while getting user details to calculate shipping quote");
    }
    ShoppingCart shoppingCart=shoppingCartFacade.getShoppingCartModel(code,merchantStore);
    if (shoppingCart == null) {
      response.sendError(404,"Cart code " + code + " does not exist");
      return null;
    }
    if (shoppingCart.getCustomerId() == null) {
      response.sendError(404,"Cart code " + code + " does not exist for exist for user " + userName);
      return null;
    }
    if (shoppingCart.getCustomerId().longValue() != customer.getId().longValue()) {
      response.sendError(404,"Cart code " + code + " does not exist for exist for user " + userName);
      return null;
    }
    ShippingSummary shippingSummary=null;
    if (quote != null) {
      shippingSummary=shippingQuoteService.getShippingSummary(quote,merchantStore);
    }
    OrderTotalSummary orderTotalSummary=null;
    OrderSummary orderSummary=new OrderSummary();
    orderSummary.setShippingSummary(shippingSummary);
    List<ShoppingCartItem> itemsSet=new ArrayList<ShoppingCartItem>(shoppingCart.getLineItems());
    orderSummary.setProducts(itemsSet);
    orderTotalSummary=orderService.caculateOrderTotal(orderSummary,customer,merchantStore,language);
    ReadableOrderTotalSummary returnSummary=new ReadableOrderTotalSummary();
    ReadableOrderSummaryPopulator populator=new ReadableOrderSummaryPopulator();
    populator.setMessages(messages);
    populator.setPricingService(pricingService);
    populator.populate(orderTotalSummary,returnSummary,merchantStore,language);
    return returnSummary;
  }
 catch (  Exception e) {
    LOGGER.error("Error while calculating order summary",e);
    try {
      response.sendError(503,"Error while calculating order summary " + e.getMessage());
    }
 catch (    Exception ignore) {
    }
    return null;
  }
}
