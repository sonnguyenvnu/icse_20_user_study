Money deposits(DateRange period){
  Money result=new Money(0,myCurrency);
  for (  Entry each : myEntries) {
    if (period.includes(each.getDate()) && each.getAmount().isPositive()) {
      result=result.add(each.getAmount());
    }
  }
  return result;
}
