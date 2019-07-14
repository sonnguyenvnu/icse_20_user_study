@Override public String visit(TimeValue value){
  return String.format("+%04d-%02d-%02dT%02d:%02d:%02dZ/%d",value.getYear(),value.getMonth(),value.getDay(),value.getHour(),value.getMinute(),value.getSecond(),value.getPrecision());
}
