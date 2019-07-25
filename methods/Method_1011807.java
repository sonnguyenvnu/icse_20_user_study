Money balance(DateRange period){
  Money result=new Money(0,myCurrency);
  for (  Entry e : myEntries) {
    if (period.includes(e.getDate())) {
      result=result.add(e.getAmount());
    }
  }
  return result;
}
