Money withdrawels(DateRange period){
  Money result=new Money(0,myCurrency);
  for (  Entry each : myEntries) {
    if (period.includes(each.getDate()) && each.getAmount().isNegative()) {
      result=result.add(each.getAmount());
    }
  }
  return result;
}
