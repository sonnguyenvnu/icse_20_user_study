@Override public Double compute(Object parm){
  Usage usageEvent=(Usage)parm;
  return usageEvent.getAmount().getAmount();
}
