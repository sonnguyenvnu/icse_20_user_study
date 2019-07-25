public void execute(DelegateExecution ctx) throws Exception {
  CreateChargeRequest request=new CreateChargeRequest();
  request.amount=(int)ctx.getVariable("remainingAmount");
  CreateChargeResponse response=rest.postForObject(stripeChargeUrl,request,CreateChargeResponse.class);
  if (response.errorCode != null && response.errorCode.length() > 0) {
    ctx.setVariable("creditCardErrorCode",response.errorCode);
    throw new BpmnError("Error_CreditCardError");
  }
  ctx.setVariable("paymentTransactionId",response.transactionId);
}
