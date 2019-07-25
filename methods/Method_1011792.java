@Override public Double compute(Object parm){
  AccountingEvent event=(AccountingEvent)parm;
  Object value=event.getAgreement().getValue(myValueName,event.getWhenOccurred());
  if (value instanceof Quantity) {
    return ((Quantity)value).getAmount();
  }
  return (Double)value;
}
