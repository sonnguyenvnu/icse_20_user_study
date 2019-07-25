public String format(PeriodType type){
  Period period=new Period(millis());
  return PeriodFormat.getDefault().withParseType(type).print(period);
}
