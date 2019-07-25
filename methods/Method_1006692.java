@RequestMapping(value={"/auth/cart/{id}/payment/init"},method=RequestMethod.POST) @ResponseBody @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="String",defaultValue="DEFAULT"),@ApiImplicitParam(name="lang",dataType="String",defaultValue="en")}) public ReadableTransaction init(@Valid @RequestBody PersistablePayment payment,@PathVariable Long id,@ApiIgnore MerchantStore merchantStore,@ApiIgnore Language language,HttpServletRequest request,HttpServletResponse response) throws Exception {
  try {
    Principal principal=request.getUserPrincipal();
    String userName=principal.getName();
    Customer customer=customerService.getByNick(userName);
    if (customer == null) {
      response.sendError(401,"Error while initializing the payment customer not authorized");
      return null;
    }
    ShoppingCart cart=shoppingCartService.getById(id,merchantStore);
    if (cart == null) {
      response.sendError(404,"Cart id " + id + " does not exist");
      return null;
    }
    if (cart.getCustomerId() == null) {
      response.sendError(404,"Cart id " + id + " does not exist for exist for user " + userName);
      return null;
    }
    if (cart.getCustomerId().longValue() != customer.getId().longValue()) {
      response.sendError(404,"Cart id " + id + " does not exist for exist for user " + userName);
      return null;
    }
    PersistablePaymentPopulator populator=new PersistablePaymentPopulator();
    populator.setPricingService(pricingService);
    Payment paymentModel=new Payment();
    populator.populate(payment,paymentModel,merchantStore,language);
    Transaction transactionModel=paymentService.initTransaction(customer,paymentModel,merchantStore);
    ReadableTransaction transaction=new ReadableTransaction();
    ReadableTransactionPopulator trxPopulator=new ReadableTransactionPopulator();
    trxPopulator.setOrderService(orderService);
    trxPopulator.setPricingService(pricingService);
    trxPopulator.populate(transactionModel,transaction,merchantStore,language);
    return transaction;
  }
 catch (  Exception e) {
    LOGGER.error("Error while initializing the payment",e);
    try {
      response.sendError(503,"Error while initializing the payment " + e.getMessage());
    }
 catch (    Exception ignore) {
    }
    return null;
  }
}
