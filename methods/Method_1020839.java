public String format(){
  Period period=new Period(millis());
  return defaultFormatter.print(period);
}
