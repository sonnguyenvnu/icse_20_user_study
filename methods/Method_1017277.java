public String pay(){
  PaymentEvent currentEvtPayload=new PaymentEvent();
  currentEvtPayload.setType(paymentOption);
  currentEvtPayload.setAmount(amount);
  currentEvtPayload.setDatetime(new Date());
switch (currentEvtPayload.getType()) {
case DEBIT:
    debitEventProducer.fire(currentEvtPayload);
  break;
case CREDIT:
creditEventProducer.fire(currentEvtPayload);
break;
default :
log.severe("invalid payment option");
break;
}
return "index";
}
