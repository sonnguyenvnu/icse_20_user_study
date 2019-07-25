public void execute(DelegateExecution ctx) throws Exception {
  CreateChargeRequest request=new CreateChargeRequest();
  request.amount=(int)ctx.getVariable("remainingAmount");
  CreateChargeResponse response=rest.postForObject(stripeChargeUrl,request,CreateChargeResponse.class);
  if (response.errorCode != null) {
    ctx.setVariable("errorCode",response.errorCode);
    throw new BpmnError("Error_PaymentError");
  }
  ctx.setVariable("paymentTransactionId",response.transactionId);
}
