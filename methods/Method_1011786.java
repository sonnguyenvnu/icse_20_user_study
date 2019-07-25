@Override public Money compute(Object parm){
  MonetaryEvent monetaryEvent=(MonetaryEvent)parm;
  return monetaryEvent.getAmount();
}
